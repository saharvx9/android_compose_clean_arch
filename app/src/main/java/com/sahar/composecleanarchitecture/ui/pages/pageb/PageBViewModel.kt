package com.sahar.composecleanarchitecture.ui.pages.pageb

import androidx.lifecycle.SavedStateHandle
import com.sahar.composecleanarchitecture.ui.pages.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PageBViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) :
    BaseViewModel() {
}