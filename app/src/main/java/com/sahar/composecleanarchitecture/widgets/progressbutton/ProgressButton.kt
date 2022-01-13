package com.sahar.composecleanarchitecture.widgets.progressbutton

import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.simform.ssjetpackcomposeprogressbuttonlibrary.SSButtonState
import com.simform.ssjetpackcomposeprogressbuttonlibrary.SSButtonType
import com.simform.ssjetpackcomposeprogressbuttonlibrary.SSJetPackComposeProgressButton

sealed class ProgressButtonState(val state: SSButtonState,val enable:Boolean = true){
    class Enable(enable: Boolean):ProgressButtonState(SSButtonState.IDLE, enable = enable)
    class Loading():ProgressButtonState(SSButtonState.LOADING, enable = false)
    class Error():ProgressButtonState(SSButtonState.FAILIURE, enable = true)
}

@Composable
fun ProgressButton(
    state:ProgressButtonState,
    text:String? = null,
    onClick:(()->Unit)? = null){
    SSJetPackComposeProgressButton(
        type = SSButtonType.CIRCLE,
        enabled = state.enable,
        speedMillis = 400,
        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
        cornerRadius = 30,
        width = (LocalConfiguration.current.screenWidthDp * 0.7).dp,
        height = 50.dp,
        text = text,
        onClick = { onClick?.invoke() },
        assetColor = Color.Red,
        buttonState = state.state
    )
}