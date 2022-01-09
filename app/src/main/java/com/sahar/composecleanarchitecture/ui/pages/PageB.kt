package com.sahar.composecleanarchitecture.ui.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sahar.composecleanarchitecture.widgets.Center
import com.sahar.composecleanarchitecture.widgets.AppBar


@ExperimentalAnimationApi
@Composable
fun PageB(navController: NavHostController = rememberAnimatedNavController()) {
    BaseComposeLifeCycle(tag = "PAGE_B") {
        Scaffold(topBar = { AppBar(title = "Page B", navController) }
        ) {
            Center {
                Button(
                    onClick = { navController.navigate("page_c/sahar12345") }) {
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

