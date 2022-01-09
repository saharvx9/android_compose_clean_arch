package com.sahar.composecleanarchitecture.widgets

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
@Composable
fun AppBar(title:String,navController: NavHostController? = null){
    TopAppBar(
        navigationIcon = {
            navController?.let {
                IconButton(onClick = { it.navigateUp() }) {
                    Icon(Icons.Rounded.ArrowBack, "")
                }
            }
        },
        title = { Text(title) })
}

@ExperimentalAnimationApi
@Preview
@Composable
fun AppBarPreview(){
    AppBar(title = "Test Title", navController = rememberAnimatedNavController())
}