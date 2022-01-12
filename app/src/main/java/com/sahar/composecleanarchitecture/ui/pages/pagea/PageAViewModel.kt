package com.sahar.composecleanarchitecture.ui.pages.pagea


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sahar.composecleanarchitecture.data.source.theme.ThemeDataSource
import com.sahar.composecleanarchitecture.ui.pages.base.BaseViewModel
import com.sahar.composecleanarchitecture.ui.pages.base.ThemeType
import com.sahar.composecleanarchitecture.ui.pages.base.toThemeValue
import com.sahar.composecleanarchitecture.widgets.dialog.AppAlert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PageAViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val themeRepository: ThemeDataSource.Local ) : BaseViewModel() {

    val lightThemeState get() = themeRepository.loadThemeType().map { it == ThemeType.LIGHT }

    fun changeTheme(light: Boolean) {
        viewModelScope.launch {
            try {
                themeRepository.setThemeType(light.toThemeValue())
            } catch (e: Exception) {
                print("failed update theme: $e")
            }
        }
    }

    fun displayDialog(){
        dialogMutableStateFlow.tryEmit(AppAlert.ErrorDialog())
    }
}