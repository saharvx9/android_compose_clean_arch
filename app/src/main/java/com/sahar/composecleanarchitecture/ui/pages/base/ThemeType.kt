package com.sahar.composecleanarchitecture.ui.pages.base

enum class ThemeType(val value:String) {
    LIGHT("LIGHT"),
    DARK("DARK"),
    SYSTEM("SYSTEM");
}

fun String?.asThemeValue():ThemeType{
    return when(this){
        ThemeType.LIGHT.value -> ThemeType.LIGHT
        ThemeType.DARK.value -> ThemeType.DARK
        else -> ThemeType.LIGHT
    }
}

fun Boolean.toThemeValue():ThemeType {
    return if(this) ThemeType.LIGHT else ThemeType.DARK
}