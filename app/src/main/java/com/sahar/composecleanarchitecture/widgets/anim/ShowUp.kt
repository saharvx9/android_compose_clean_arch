package com.sahar.composecleanarchitecture.widgets.anim

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.unit.IntOffset
import com.sahar.composecleanarchitecture.ui.pages.BaseComposeLifeCycle
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
    val animSlide = tween<IntOffset>(durationMillis = 500)
    val animFade = tween<Float>(durationMillis = 300)
    val offsetY: (fullHeight: Int) -> Int = { 50 }

    // LaunchedEffect: run suspend functions in the scope of a composable
    // Warning: LaunchedEffect(true) is as suspicious as a while(true).
    // Even though there are valid use cases for it, always pause and make sure that's what you need.
    LaunchedEffect(true) {
        delay(delayAnim)
        value = display
        cancel()
    }

    BaseComposeLifeCycle(onStart = {  }, onPause = { value = !value }){
        AnimatedVisibility(
            visible = value,
            enter = slideInVertically(animationSpec = animSlide, initialOffsetY = offsetY) + fadeIn(animationSpec = animFade),
            exit = slideOutVertically(animationSpec = animSlide, targetOffsetY = offsetY) + fadeOut(animationSpec = animFade)
        ) {
            content()
        }
    }

}
