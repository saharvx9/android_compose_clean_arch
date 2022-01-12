package com.sahar.composecleanarchitecture.ui.pages.articlelist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sahar.composecleanarchitecture.ui.pages.base.BaseComposeLifeCycle
import com.sahar.composecleanarchitecture.widgets.SearchAppBar
import com.sahar.composecleanarchitecture.ui.pages.articlelist.ArticlesState.*
import com.sahar.composecleanarchitecture.ui.pages.articlelist.widgets.ArticleList
import com.sahar.composecleanarchitecture.widgets.Center


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun ArticlesListPage(
    viewModel: ArticlesListViewModel = hiltViewModel(),
    navController: NavHostController = rememberAnimatedNavController(),
) {
    viewModel.start()
    BaseComposeLifeCycle(viewModel = viewModel, navController = navController) {
        Scaffold(
            topBar = { AppBar(navController, viewModel = viewModel) }) {

            val articlesState = viewModel.articleState.collectAsState(LoadingState)
            ArticleListHandler(articlesState.value, viewModel)
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun AppBar(navController: NavHostController, viewModel: ArticlesListViewModel) {
    var textState by remember { mutableStateOf(TextFieldValue()) }
    SearchAppBar(
        textFieldValue = textState,
        navController = navController,
        searchHint = "Search...",
        onTextChanged = {
            textState = it
            viewModel.search(it.text)
        },
    )
}


@ExperimentalMaterialApi
@Composable
private fun ArticleListHandler(state: ArticlesState, viewModel: ArticlesListViewModel) {
    when (state) {
        is ArticlesListResult -> ArticleList(state.list) { viewModel.onClickItem(it) }
        is ErrorState -> Center {
            Text(
                text = state.message,
                style = TextStyle(fontSize = 20.sp, color = MaterialTheme.colors.error)
            )
        }
        LoadingState -> Center { CircularProgressIndicator() }
    }
}


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
@Preview
fun PageCPreview() {
    ArticlesListPage()
}