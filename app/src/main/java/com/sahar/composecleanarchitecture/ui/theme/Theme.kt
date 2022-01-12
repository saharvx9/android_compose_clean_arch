package com.sahar.composecleanarchitecture.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sahar.composecleanarchitecture.ui.pages.base.ThemeType

private val DarkColorPalette = darkColors(
    primary = darkGray,
    primaryVariant = Teal200,
    secondary = White,
    surface = Black,
    onBackground = YellowAlpha,
    error = Color.Red,
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Black,
    surface = White, //text
    onBackground = White,
    error = Color.Red,


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ComposeCleanArchitectureTheme(
    systemUiController: SystemUiController = rememberSystemUiController(),
    darkTheme: ThemeType,
    content: @Composable () -> Unit
) {
    val colors = when(darkTheme){
        ThemeType.LIGHT -> LightColorPalette
        ThemeType.DARK -> DarkColorPalette
        ThemeType.SYSTEM -> if(isSystemInDarkTheme()) DarkColorPalette else LightColorPalette
    }
    systemUiController.setSystemBarsColor(colors.background)
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}