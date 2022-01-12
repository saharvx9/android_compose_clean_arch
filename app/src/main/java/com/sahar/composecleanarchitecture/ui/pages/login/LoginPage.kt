package com.sahar.composecleanarchitecture.ui.pages.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sahar.composecleanarchitecture.ui.pages.base.BaseComposeLifeCycle
import com.sahar.composecleanarchitecture.widgets.AppBar
import com.sahar.composecleanarchitecture.widgets.EditText
import com.sahar.composecleanarchitecture.widgets.anim.ShowUp
import com.sahar.composecleanarchitecture.widgets.progressbutton.ProgressButton
import com.sahar.composecleanarchitecture.widgets.progressbutton.ProgressButtonState
import com.simform.ssjetpackcomposeprogressbuttonlibrary.SSButtonState
import com.simform.ssjetpackcomposeprogressbuttonlibrary.SSButtonType
import com.simform.ssjetpackcomposeprogressbuttonlibrary.SSJetPackComposeProgressButton

@ExperimentalAnimationApi
@Composable
fun LoginPage(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController = rememberAnimatedNavController()
) {
    BaseComposeLifeCycle(tag = "LoginPage", viewModel = viewModel) {
        Scaffold(topBar = { AppBar(title = "Login Page", navController) }) {
            Body(viewModel)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun Body(viewModel: LoginViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val userNameState: LoginFieldState? by viewModel.userNameLiveData.observeAsState()
        val passState: LoginFieldState? by viewModel.passwordLiveData.observeAsState()
        val progressButtonState: ProgressButtonState by viewModel.buttonState.collectAsState(ProgressButtonState.Enable(false))
        ShowUp() {
            EditText(
                hint = "User name",
                error = userNameState?.error,
                onValueChange = { viewModel.onValueChange(it, LoginFieldType.USER_NAME_EDIT_TEXT) })
        }


        Spacer(modifier = Modifier.size(10.dp))
        ShowUp() {
            EditText(
                hint = "Password",
                passwordStyle = true,
                error = passState?.error,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                onValueChange = { viewModel.onValueChange(it, LoginFieldType.PASSWORD_EDIT_TEXT) })
        }

        Spacer(modifier = Modifier.size(10.dp))

        ShowUp {
            ProgressButton(progressButtonState,
                text = "Submit",
                onClick = { viewModel.click() })
        }
    }
}


@ExperimentalAnimationApi
@Preview
@Composable
fun LoginPagePreview() {
    LoginPage(viewModel())
}

