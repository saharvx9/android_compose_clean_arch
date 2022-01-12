package com.sahar.composecleanarchitecture.ui.navbuilder

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.*
import com.google.accompanist.navigation.animation.composable
import com.sahar.composecleanarchitecture.ui.pages.login.LoginPage
import com.sahar.composecleanarchitecture.ui.pages.pagea.PageA
import com.sahar.composecleanarchitecture.ui.pages.pageb.PageB
import com.sahar.composecleanarchitecture.ui.pages.articlelist.ArticlesListPage
import com.sahar.composecleanarchitecture.ui.pages.splash.SplashPage

import com.sahar.composecleanarchitecture.ui.navbuilder.Page.*

/**
 *
 * NavAppBuilder
 * <p>
 * Handle for all main navigation + animation
 *
 */
@ExperimentalAnimationApi
@ExperimentalMaterialApi
class NavAppBuilder(private val navController: NavHostController) {


    companion object {
        fun buildNavGraph(
            navController: NavHostController,
            startDestination: String = SplashPage.route
        ): NavGraph {
            val builder = NavAppBuilder(navController).buildNavGraph()
            return navController.createGraph(startDestination, null, builder)
        }
    }


    fun buildNavGraph(): NavGraphBuilder.() -> Unit {
        return {
            splashPage()
            pageA()
            pageB()
            articlesListPage()
            pageLogin()
        }
    }

    private fun NavGraphBuilder.splashPage() {
        return composable(
            SplashPage.route,
        ) {
            SplashPage(navController = navController)
        }
    }


    private fun NavGraphBuilder.pageA() {
        return composable(
            PageA.route,
            enterTransition = {
                slideInHorizontally() + fadeIn(animationSpec = tween(200))
            },
        ) {
            PageA(navController = navController)
        }
    }

    private fun NavGraphBuilder.pageB() {
        //PageB
        return composable("${PageB( "").route}/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType }),
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(500),
                    initialOffsetX = { 1000 })
            }) {
            PageB(navController = navController, userId = it.arguments?.getString("userId"))
        }
    }

    private fun NavGraphBuilder.articlesListPage() {
        //Page C
        return composable(ArticlesListPage.route,
            enterTransition = {
                slideInVertically(
                    animationSpec = tween(500),
                    initialOffsetY = { 1000 })
            }) {
            ArticlesListPage(
                navController = navController,
            )
        }

    }

    private fun NavGraphBuilder.pageLogin() {
        //Login Page
        return composable(LoginPage.route,
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(500),
                    initialOffsetX = { 1000 })
            },
            exitTransition = {
                slideOutHorizontally(animationSpec = tween(250), targetOffsetX = { 100 })
            }
        ) {
            LoginPage(navController = navController)
        }

    }
}