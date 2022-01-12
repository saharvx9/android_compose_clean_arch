package com.sahar.composecleanarchitecture.ui.pages.splash

import androidx.lifecycle.SavedStateHandle
import com.sahar.composecleanarchitecture.ui.pages.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject constructor(private val savedStateHandle: SavedStateHandle) : BaseViewModel() {

}