package com.sahar.composecleanarchitecture.ui.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sahar.composecleanarchitecture.ui.theme.ComposeCleanArchitectureTheme

@Composable
fun BaseComposeLifeCycle(
    tag:String? = null,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onCreate: (() -> Unit)? = null, // Send the 'started' analytics event
    onStart: (() -> Unit)? = null, // Send the 'started' analytics event
    onResume: (() -> Unit)? = null, // Send the 'started' analytics event
    onPause: (() -> Unit)? = null,
    onStop: (() -> Unit)? = null,
    onDestroy: (() -> Unit)? = null,
    content: @Composable () -> Unit = {},
) {
    // Safely update the current lambdas when a new one is provided
    val currentOnCreate by rememberUpdatedState(onCreate)
    val currentOnStart by rememberUpdatedState(onStart)
    val currentOnResume by rememberUpdatedState(onResume)
    val currentOnPause by rememberUpdatedState(onPause)
    val currentOnStop by rememberUpdatedState(onStop)
    val currentOnDestroy by rememberUpdatedState(onDestroy)

    // If `lifecycleOwner` changes, dispose and reset the effect
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for sending analytics events
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    tag?.let { println("$it : ON_CREATE") }
                    currentOnCreate?.invoke()
                }
                Lifecycle.Event.ON_START -> {
                    tag?.let { println("$it : ON_START") }
                    currentOnStart?.invoke()
                }
                Lifecycle.Event.ON_RESUME -> {
                    tag?.let { println("$it : ON_RESUME") }
                    currentOnResume?.invoke()
                }
                Lifecycle.Event.ON_PAUSE -> {
                    tag?.let { println("$it : ON_PAUSE") }
                    currentOnPause?.invoke()
                }
                Lifecycle.Event.ON_STOP -> {
                    tag?.let { println("$it : ON_STOP") }
                    currentOnStop?.invoke()
                }
                Lifecycle.Event.ON_DESTROY -> {
                    tag?.let { println("$it : ON_DESTROY") }
                    currentOnDestroy?.invoke()
                }
                else -> Unit
            }

        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    /* Home screen content */
    ComposeCleanArchitectureTheme(rememberSystemUiController()) {
        content()
    }
}