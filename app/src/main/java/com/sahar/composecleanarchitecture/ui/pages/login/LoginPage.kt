package com.sahar.composecleanarchitecture.ui.pages.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sahar.composecleanarchitecture.ui.pages.BaseComposeLifeCycle
import com.sahar.composecleanarchitecture.widgets.AppBar
import com.sahar.composecleanarchitecture.widgets.EditText
import com.sahar.composecleanarchitecture.widgets.anim.ShowUp
import com.simform.ssjetpackcomposeprogressbuttonlibrary.SSButtonState
import com.simform.ssjetpackcomposeprogressbuttonlibrary.SSButtonType
import com.simform.ssjetpackcomposeprogressbuttonlibrary.SSJetPackComposeProgressButton

@ExperimentalAnimationApi
@Composable
fun LoginPage(
    loginViewModel: LoginViewModel,
    navController: NavHostController = rememberAnimatedNavController()
) {
    BaseComposeLifeCycle(tag = "LoginPage",onStart = {}) {
        Scaffold(topBar = { AppBar(title = "Login Page",navController) }) {
            Body(loginViewModel)
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
        var submitButtonState by remember { mutableStateOf(SSButtonState.IDLE) }

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
                onValueChange = { viewModel.onValueChange(it, LoginFieldType.PASSWORD_EDIT_TEXT) })
        }
        Spacer(modifier = Modifier.size(10.dp))
       ShowUp(delayAnim = 500){
           SSJetPackComposeProgressButton(
               type = SSButtonType.CIRCLE,
               enabled = false,
               width = (LocalConfiguration.current.screenWidthDp * 0.7).dp,
               height = 50.dp,
               onClick = {
                   //Perform action on click of button and make it's state to LOADING
                   submitButtonState = SSButtonState.LOADING
               },
               assetColor = Color.Red,
               buttonState = submitButtonState
           )
       }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun LoginPagePreview() {
    LoginPage(viewModel())
}