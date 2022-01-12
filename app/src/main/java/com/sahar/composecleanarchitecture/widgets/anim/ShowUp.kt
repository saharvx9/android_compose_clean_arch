package com.sahar.composecleanarchitecture.widgets.anim

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.unit.IntOffset
import com.sahar.composecleanarchitecture.ui.pages.base.BaseComposeLifeCycle
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay


@ExperimentalAnimationApi
@Composable
fun ShowUp(
    display: Boolean = true,
    delayAnim:Long = 300,
    content: @Composable () -> Unit = {}
) {
    var value by remember { mutableStateOf(false) }
    val animSlide = tween<IntOffset>(durationMillis = 500, easing = LinearOutSlowInEasing)
    val animFade = tween<Float>(durationMillis = 200, easing = LinearOutSlowInEasing)
    val offsetY: (fullHeight: Int) -> Int = { 50 }

    // LaunchedEffect: run suspend functions in the scope of a composable
    // Warning: LaunchedEffect(true) is as suspicious as a while(true).
    // Even though there are valid use cases for it, always pause and make sure that's what you need.
        LaunchedEffect(display) {
            delay(delayAnim)
            value = display
        }

    AnimatedVisibility(
        visible = value,
        enter = slideInVertically(animationSpec = animSlide, initialOffsetY = offsetY) + fadeIn(animationSpec = animFade),
        exit = slideOutVertically(animationSpec = animSlide, targetOffsetY = offsetY) + fadeOut(animationSpec = animFade)
    ) {
        content()
    }

}
