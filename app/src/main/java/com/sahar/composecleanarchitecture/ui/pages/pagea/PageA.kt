package com.sahar.composecleanarchitecture.ui.pages.pagea

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sahar.composecleanarchitecture.ui.navbuilder.Page
import com.sahar.composecleanarchitecture.ui.navbuilder.Page.*
import com.sahar.composecleanarchitecture.ui.pages.base.BaseComposeLifeCycle
import com.sahar.composecleanarchitecture.widgets.Center
import com.sahar.composecleanarchitecture.widgets.anim.ShowUp
import com.sahar.composecleanarchitecture.widgets.customswitch.CustomSwitch
import com.sahar.composecleanarchitecture.widgets.customswitch.CustomSwitchDefaults

@ExperimentalAnimationApi
@Composable
fun PageA(
    viewModel: PageAViewModel = hiltViewModel(),
    navController: NavHostController = rememberAnimatedNavController()
) {
    BaseComposeLifeCycle(
        tag = PageA.route,
        onCreate = { },
        viewModel = viewModel,
        navController = navController
    ) {
        Scaffold(topBar = { AppBar(viewModel) }
        ) {
            Container(viewModel)
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
private fun PageAPreview() {
    PageA(viewModel = viewModel())
}

@Composable
private fun AppBar(viewModel: PageAViewModel) {
    TopAppBar(title = { Text("App bar") }, actions = {
        ThemeSwitcher(viewModel)
    })
}

@Composable
private fun ThemeSwitcher(viewModel: PageAViewModel) {

    var check: Boolean = viewModel.lightThemeState.collectAsState(initial = false).value

    CustomSwitch(
        checked = check, onCheckedChange = {
            check = it
            viewModel.changeTheme(check)
        }, colors = CustomSwitchDefaults.colors(
            checkedTrackColor = Color.Gray,
            uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f)
        )
    )
}


@ExperimentalAnimationApi
@Composable
private fun Container(viewModel: PageAViewModel) {
    Center {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonNavigation(title = "go to login page", route = LoginPage, viewModel)
            Spacer(modifier = Modifier.height(10.dp))
            ButtonNavigation(title = "go to articles list", route = ArticlesListPage, viewModel)
            Spacer(modifier = Modifier.height(10.dp))
            ButtonNavigation(title = "go to page b with arg", route = PageB(arg = "sahar1234"), viewModel)
            Spacer(modifier = Modifier.height(10.dp))
            ButtonDisplayDialog(viewModel = viewModel)
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun ButtonNavigation(title: String, route: Page, viewModel: PageAViewModel) {
    ShowUp {
        Button(
            onClick = { viewModel.navigatePage(route) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = title, color = MaterialTheme.colors.secondary)
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun ButtonDisplayDialog(viewModel: PageAViewModel) {
    ShowUp {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant),
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.displayDialog() }) {
            Text(text = "display dialog", color = MaterialTheme.colors.secondary)
        }
    }
}

