package com.sahar.composecleanarchitecture.ui.pages.pageb

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sahar.composecleanarchitecture.ui.navbuilder.Page
import com.sahar.composecleanarchitecture.ui.navbuilder.Page.ArticlesListPage
import com.sahar.composecleanarchitecture.ui.pages.base.BaseComposeLifeCycle
import com.sahar.composecleanarchitecture.utils.ext.toast
import com.sahar.composecleanarchitecture.widgets.AppBar
import com.sahar.composecleanarchitecture.widgets.Center


@ExperimentalAnimationApi
@Composable
fun PageB(
    viewModel: PageBViewModel = hiltViewModel(),
    navController: NavHostController = rememberAnimatedNavController(),
    context:Context = LocalContext.current,
    userId: String? = null) {
    BaseComposeLifeCycle(tag = Page.PageB().route, viewModel = viewModel, navController = navController) {
        Scaffold(topBar = { AppBar(title = "Display arg", navController) }
        ) {
            Center {
                Button(
                    onClick = { context.toast("show user id: $userId") }) {
                    Text("go page c")
                }
            }
        }
    }

}


@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
private fun PageBPreview() {
    PageB()
}

