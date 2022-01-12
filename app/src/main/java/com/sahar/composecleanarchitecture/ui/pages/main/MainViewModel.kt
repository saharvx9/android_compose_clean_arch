package com.sahar.composecleanarchitecture.ui.pages.main

import com.sahar.composecleanarchitecture.data.source.theme.ThemeDataSource
import com.sahar.composecleanarchitecture.ui.pages.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val themeRepository: ThemeDataSource.Local) : BaseViewModel() {

    val themeState get() =  themeRepository.loadThemeType()

}