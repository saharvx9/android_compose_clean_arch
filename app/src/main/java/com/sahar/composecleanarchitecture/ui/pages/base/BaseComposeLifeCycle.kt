package com.sahar.composecleanarchitecture.ui.pages.base

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.sahar.composecleanarchitecture.utils.ext.logInfo
import com.sahar.composecleanarchitecture.widgets.dialog.AppAlertDialog

@Composable
fun <T : BaseViewModel> BaseComposeLifeCycle(
    tag: String? = null,
    viewModel: T,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onCreate: (() -> Unit)? = null, // Send the 'started' analytics event
    onStart: (() -> Unit)? = null, // Send the 'started' analytics event
    onResume: (() -> Unit)? = null, // Send the 'started' analytics event
    onPause: (() -> Unit)? = null,
    onStop: (() -> Unit)? = null,
    onDestroy: (() -> Unit)? = null,
    navController: NavHostController? = null,
    content: @Composable () -> Unit = {},
) {
    // Safely update the current lambdas when a new one is provided
    val currentOnCreate by rememberUpdatedState(onCreate)
    val currentOnStart by rememberUpdatedState(onStart)
    val currentOnResume by rememberUpdatedState(onResume)
    val currentOnPause by rememberUpdatedState(onPause)
    val currentOnStop by rememberUpdatedState(onStop)
    val currentOnDestroy by rememberUpdatedState(onDestroy)

    //Dialog state
    AlertDialogHandler(viewModel)

    //Navigation state
    val pageState = viewModel.navigatePageState.collectAsState(null)
    LaunchedEffect(pageState.value) {
        pageState.value?.apply {
            navController?.navigate(route = destination){
                if(removeAllStack) popUpTo(navController.currentDestination?.route?:""){
                    //That mean it remove the current page we are at
                    inclusive = true
                }
            }
        }
    }


    // If `lifecycleOwner` changes, dispose and reset the effect
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for sending analytics events
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    tag?.let { logInfo("$it : ON_CREATE") }
                    currentOnCreate?.invoke()
                }
                Lifecycle.Event.ON_START -> {
                    tag?.let { logInfo("$it : ON_START") }
                    currentOnStart?.invoke()
                }
                Lifecycle.Event.ON_RESUME -> {
                    tag?.let { logInfo("$it : ON_RESUME") }
                    currentOnResume?.invoke()
                }
                Lifecycle.Event.ON_PAUSE -> {
                    tag?.let { logInfo("$it : ON_PAUSE") }
                    currentOnPause?.invoke()
                }
                Lifecycle.Event.ON_STOP -> {
                    tag?.let { logInfo("$it : ON_STOP") }
                    currentOnStop?.invoke()
                }
                Lifecycle.Event.ON_DESTROY -> {
                    tag?.let { logInfo("$it : ON_DESTROY") }
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
    content()
}

@Composable
private fun <T : BaseViewModel> AlertDialogHandler(viewModel: T) {
    val dialogData = viewModel.dialogState.collectAsState(null)
    AppAlertDialog(dialogData.value)
}

