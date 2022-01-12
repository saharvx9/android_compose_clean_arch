package com.sahar.composecleanarchitecture.widgets

import androidx.compose.animation.*
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.UnfocusedIndicatorLineOpacity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahar.composecleanarchitecture.utils.ext.drawColoredShadow

@Composable
fun EditText(
    modifier: Modifier = Modifier,
    hint: String? = null,
    removeUnderLine: Boolean = true,
    passwordStyle: Boolean = false,
    error: String? = null,
    lines: Int? = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    onValueChange: ((String) -> Unit)? = null
) {
    val focusManager = LocalFocusManager.current
    var text by remember { mutableStateOf("") }
    val hintState by remember { mutableStateOf(hint ?: "") }
    var passwordVisibility by remember { mutableStateOf(false) }
//    error.takeIf { !it.isNullOrEmpty() }?.let { hintState = it } ?: run { hintState = hint?:"" }


    val unfocusedIndicatorColor =
        MaterialTheme.colors.onSurface.copy(alpha = UnfocusedIndicatorLineOpacity)
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            modifier = modifier.drawColoredShadow(Color.Gray),
            value = text,
            label = hintState.let { { Text(text = it) } },
            shape = RoundedCornerShape(10.dp),
            maxLines = lines ?: Int.MAX_VALUE,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = if (passwordStyle) KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = keyboardOptions.imeAction
            ) else keyboardOptions,
            visualTransformation = if (passwordStyle) {
                if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
            } else VisualTransformation.None,
            trailingIcon = {
                if (passwordStyle) TrailingIconPassword(
                    passwordVisibility,
                    onClick = { passwordVisibility = !passwordVisibility })
            },
            isError = !error.isNullOrEmpty(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.onBackground,
                focusedIndicatorColor = if (removeUnderLine) Color.Transparent else MaterialTheme.colors.primary.copy(
                    alpha = ContentAlpha.high
                ),
                unfocusedIndicatorColor = if (removeUnderLine) Color.Transparent else unfocusedIndicatorColor,
                disabledIndicatorColor = if (removeUnderLine) Color.Transparent else unfocusedIndicatorColor.copy(
                    alpha = ContentAlpha.disabled
                )
            ),
            onValueChange = {
                text = it
                onValueChange?.invoke(it)
            })
        ErrorText(error)
    }
}

@Composable
fun EditTextOutLine(
    modifier: Modifier = Modifier,
    passwordStyle: Boolean = false,
    hint: String? = null,
    error: String? = null,
    onValueChange: ((String) -> Unit)? = null
) {
    var text by remember { mutableStateOf("") }
    val hintState by remember { mutableStateOf(hint ?: "") }
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.Center) {
        OutlinedTextField(
            modifier = modifier,
            value = text,
            label = hintState.let { { Text(text = it) } },
            singleLine = true,
            visualTransformation = if (passwordStyle) {
                if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
            } else VisualTransformation.None,
            keyboardOptions = if (passwordStyle) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
            shape = RoundedCornerShape(10.dp),
            trailingIcon = {
                if (passwordStyle) TrailingIconPassword(
                    passwordVisibility,
                    onClick = { passwordVisibility = !passwordVisibility })
            },
            isError = !error.isNullOrEmpty(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                errorBorderColor = MaterialTheme.colors.error,
                backgroundColor = MaterialTheme.colors.background,
                focusedBorderColor = Color.Black,
                disabledBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            ),
            onValueChange = {
                text = it
                onValueChange?.invoke(it)
            })
        ErrorText(error)
    }
}

@Composable
private fun TrailingIconPassword(visible: Boolean, onClick: () -> Unit) {
    val image = if (visible) Icons.Filled.Visibility
    else Icons.Filled.VisibilityOff

    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = image, "",
            tint = MaterialTheme.colors.secondary
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ErrorText(error: String?) {
    AnimatedVisibility(
        visible = !error.isNullOrEmpty(),
        enter = fadeIn() + scaleIn(),
        exit = fadeOut() + scaleOut()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = error ?: "",
            style = TextStyle(color = MaterialTheme.colors.error, fontSize = 10.sp)
        )
    }
}


@Preview
@Composable
fun EditTextPreview() {
    Column(Modifier.background(Color.White)) {
        EditText(
            hint = "hint text field",
            modifier = Modifier.padding(10.dp),
            removeUnderLine = true
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        EditTextOutLine(hint = "hint out line", modifier = Modifier.padding(10.dp))
    }

}