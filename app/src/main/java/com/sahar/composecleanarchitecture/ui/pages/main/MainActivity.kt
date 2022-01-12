package com.sahar.composecleanarchitecture.ui.pages.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sahar.composecleanarchitecture.ui.navbuilder.NavAppBuilder
import com.sahar.composecleanarchitecture.ui.pages.base.ThemeType
import com.sahar.composecleanarchitecture.ui.theme.ComposeCleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint


//Get screen dimension for response ui
//LocalConfiguration.current.screenWidthDp

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()

            //Note that any instance of StateFlow already behaves as if distinctUtilChanged operator is applied to it,
            // so applying distinctUntilChanged to a StateFlow has no effect. See StateFlow documentation on Operator Fusion.
            // Also, repeated application of distinctUntilChanged operator on any flow has no effect.
            val darkTheme: ThemeType by mainViewModel.themeState.collectAsState(ThemeType.SYSTEM)

            ComposeCleanArchitectureTheme(darkTheme = darkTheme) {
                AnimatedNavHost(
                    navController = navController,
                    graph = NavAppBuilder.buildNavGraph(navController = navController)
                )
            }
        }
    }

}