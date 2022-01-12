package com.sahar.composecleanarchitecture.widgets.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sahar.composecleanarchitecture.widgets.dialog.AppAlert.ErrorDialog

@Composable
fun AppAlertDialog(
    data: AppAlert?,
    onClickPositive: (() -> Unit)? = null,
    onNegativePositive: (() -> Unit)? = null,
) {

    val dialogHandler = remember { mutableStateOf(false) }
    LaunchedEffect(data) {
        dialogHandler.value = data != null
    }

    if (!dialogHandler.value) return

    Dialog(
        onDismissRequest = {
            dialogHandler.value = false
            onNegativePositive?.invoke()
        },
        properties = DialogProperties(
            dismissOnBackPress = data!!.cancelable,
            dismissOnClickOutside = data.cancelable
        )
    ) {
        DialogBody(
            data = data,
            onClickPositive = onClickPositive,
            onNegativePositive = onNegativePositive,
            display = dialogHandler
        )
    }
}

@Composable
private fun DialogBody(
    data: AppAlert,
    onClickPositive: (() -> Unit)? = null,
    onNegativePositive: (() -> Unit)? = null,
    display: MutableState<Boolean>,
) {
    Card(
        modifier = Modifier.defaultMinSize(),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.primaryVariant,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = data.icon, contentDescription = "dialog_icon",
                Modifier.size(75.dp)
            )

            //Title
            (data.title?.let { stringResource(id = it) } ?: run { data.titleStr })
                ?.let { TextDialog(text = it, size = 20) }

            Spacer(Modifier.height(10.dp))

            //Subtitle
            (data.subtitle?.let { stringResource(id = it) } ?: run { data.subtitleStr })
                ?.let { TextDialog(text = it, size = 15) }

            if (!data.isNegativeButton) {
                ButtonDialog(
                    Modifier.fillMaxWidth(),
                    text = data.positiveButtonTitle,
                    onClick = onClickPositive,
                    display = display
                )
            } else {
                Row(Modifier.fillMaxWidth()) {
                    //Positive button
                    ButtonDialog(
                        Modifier.weight(2f, true),
                        text = data.positiveButtonTitle,
                        onClick = onClickPositive,
                        display = display
                    )
                    Spacer(modifier = Modifier.weight(1f, true))

                    //Negative button
                    ButtonDialog(
                        Modifier.weight(2f, true),
                        text = data.negativeButtonTitle,
                        onClick = onNegativePositive,
                        display = display
                    )
                }
            }
        }
    }
}

@Composable
private fun TextDialog(
    text: String? = null,
    size: Int = 14,
    color: Color = MaterialTheme.colors.secondary
) {
    Text(text = text ?: "", fontSize = size.sp, color = color)
}

@Composable
private fun ButtonDialog(
    modifier: Modifier = Modifier,
    text: Int,
    onClick: (() -> Unit)?,
    display: MutableState<Boolean>
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onBackground,
        ),
        onClick = {
            onClick?.invoke()
            display.value = false
        }) {
        TextDialog(text = stringResource(id = text), color = MaterialTheme.colors.onSecondary)
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun AppDialogPreview() {
    val openDialog = remember { mutableStateOf(true) }
    AppAlertDialog(ErrorDialog())
}