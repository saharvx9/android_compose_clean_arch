package com.sahar.composecleanarchitecture.ui.pages.splash

import android.view.animation.BounceInterpolator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sahar.composecleanarchitecture.ui.pages.base.BaseComposeLifeCycle
import com.sahar.composecleanarchitecture.ui.navbuilder.Page.*
import com.sahar.composecleanarchitecture.R
import com.sahar.composecleanarchitecture.utils.ext.toEasing
import com.sahar.composecleanarchitecture.widgets.Center
import kotlinx.coroutines.delay


@ExperimentalAnimationApi
@Composable
fun SplashPage(
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavHostController = rememberAnimatedNavController()
) {
    BaseComposeLifeCycle(
        viewModel = viewModel,
        tag = SplashPage.route,
        navController = navController
    ) {
        Surface {
            Center {
                LogoAnim {
                    //finishAnim callback
                    viewModel.navigatePage(PageA.apply { removeAllStack = true })
                }
            }
        }
    }
}


@ExperimentalAnimationApi
@Composable
private fun LogoAnim(finishAnim:()->Unit) {
    val scale = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    LaunchedEffect(scale){
        delay(1000)
        finishAnim()
    }

    AnimatedVisibility(
        modifier = Modifier.padding(50.dp),
        visibleState = scale,
        enter = scaleIn(animationSpec = tween(800, easing = BounceInterpolator().toEasing())) + fadeIn(animationSpec = tween(400))
    ) {
        Image(painter = painterResource(id = R.drawable.compose_logo_hd), contentDescription = "")
    }
}


@ExperimentalAnimationApi
@Preview
@Composable
private fun SplashPagePreview(){
    SplashPage(viewModel = viewModel())
}
