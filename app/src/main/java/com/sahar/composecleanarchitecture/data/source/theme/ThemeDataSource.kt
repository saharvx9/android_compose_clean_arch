package com.sahar.composecleanarchitecture.data.source.theme

import com.sahar.composecleanarchitecture.ui.pages.base.ThemeType
import kotlinx.coroutines.flow.Flow

interface ThemeDataSource {

    interface Local{
        fun loadThemeType():Flow<ThemeType>
        suspend fun setThemeType(type:ThemeType)
    }
}