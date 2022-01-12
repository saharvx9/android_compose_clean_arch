package com.sahar.composecleanarchitecture.ui.pages.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahar.composecleanarchitecture.ui.navbuilder.Page
import com.sahar.composecleanarchitecture.utils.ext.throttleFirst
import com.sahar.composecleanarchitecture.widgets.dialog.AppAlert
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


abstract class BaseViewModel : ViewModel() {

    protected val dialogMutableStateFlow = MutableSharedFlow<AppAlert?>(extraBufferCapacity = 1)
    val dialogState: Flow<AppAlert?> get() = dialogMutableStateFlow.throttleFirst(300)
    private val navigatePageMutableState = MutableSharedFlow<Page?>(extraBufferCapacity = 1)
    val navigatePageState get():Flow<Page?> = navigatePageMutableState

    open fun start() = Unit

    fun navigatePage(page: Page?) {
        navigatePageMutableState.tryEmit(page)
    }

    protected inline fun <T> Flow<T>.collectInScope(crossinline block: (T) -> Unit) {
        viewModelScope.launch {
            collect { block(it) }
        }
    }
}