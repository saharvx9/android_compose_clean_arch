package com.sahar.composecleanarchitecture.ui.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sahar.composecleanarchitecture.utils.toast
import com.sahar.composecleanarchitecture.widgets.SearchAppBar

@ExperimentalAnimationApi
@Composable
fun PageC(navController: NavController = rememberAnimatedNavController(),userId:String? = null) {
    val ctx = LocalContext.current
    BaseComposeLifeCycle(){
        Scaffold(
            topBar = { AppBar() }) {
            Button(onClick = {
                ctx.toast("show userId:$userId")
            }) {

            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun AppBar() {
    SearchAppBar(
        onTextChanged = {},
        searchHint = "test"
    ) {

    }
}



@Composable
@Preview
fun PageCPreview() {

}