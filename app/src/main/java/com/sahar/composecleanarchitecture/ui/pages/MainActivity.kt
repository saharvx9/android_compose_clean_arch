package com.sahar.composecleanarchitecture.ui.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sahar.composecleanarchitecture.ui.pages.login.LoginPage
import com.sahar.composecleanarchitecture.ui.pages.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginViewModel:LoginViewModel = viewModel()
            val navController = rememberAnimatedNavController()

            BackHandler(enabled = true){
                print("on back press")
            }

            AnimatedNavHost(navController = navController, startDestination = "page_a") {
                //PageA
                composable("page_a",
                    enterTransition = { _, _ ->
                        // Let's make for a really long fade in
                        slideInHorizontally()
                    },
                    exitTransition = { initial, _ ->
                        // Check to see if the previous screen is in the login graph
                        fadeOut(animationSpec = tween(200)) + slideOutHorizontally(
                            animationSpec = tween(
                                500
                            ), targetOffsetX = { 1000 })
                    }
                ) { PageA(navController) }

                //PageB
                composable("page_b",
                    enterTransition = { _, _ ->
                        slideInHorizontally(
                            animationSpec = tween(500),
                            initialOffsetX = { 1000 })
                    }) { PageB(navController) }

                //Page C
                composable("page_c/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.StringType }),
                    enterTransition = { _, _ ->
                        slideInHorizontally(
                            animationSpec = tween(500),
                            initialOffsetX = { 1000 })
                    }) { PageC(navController, it.arguments?.getString("userId")) }

                //Login Page
                composable("login_page",
                    enterTransition = { _, _ ->
                        slideInHorizontally(
                            animationSpec = tween(500),
                            initialOffsetX = { 1000 })
                    }) {
                    LoginPage(loginViewModel,navController)
                }

            }
        }
    }

    @Composable
    fun PageA(navController: NavHostController = rememberAnimatedNavController()) {
        BaseComposeLifeCycle(tag = "PAGE_A",onCreate = { }) {
            Scaffold(topBar = { AppBar() }
            ) {
                Container(navController)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun PageAPreview() {
        PageA()
    }

    @Composable
    private fun AppBar() {
        TopAppBar(title = { Text("App bar") })
    }

    @Composable
    private fun Container(navController: NavHostController) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant),
                    onClick = { navController.navigate("login_page") },
                ) {
                    Text(text = "go page b")
                }
            }
        }
    }

}