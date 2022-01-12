package com.sahar.composecleanarchitecture.ui.pages.login

import androidx.lifecycle.*
import com.sahar.composecleanarchitecture.data.source.user.UserRepository
import com.sahar.composecleanarchitecture.ui.pages.base.BaseViewModel
import com.sahar.composecleanarchitecture.widgets.progressbutton.ProgressButtonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val userNameState = MutableLiveData<LoginFieldState>()
    val userNameLiveData get():LiveData<LoginFieldState> = userNameState

    private val passwordState = MutableLiveData<LoginFieldState>()
    val passwordLiveData get():LiveData<LoginFieldState> = passwordState

    private val buttonShareFlow = MutableSharedFlow<ProgressButtonState>(extraBufferCapacity = 1)
    val buttonState get():Flow<ProgressButtonState> = buttonShareFlow


    init {
        viewModelScope.launch {
            userNameState.asFlow().combine(passwordState.asFlow()) { userName, pass ->
                Pair(userName.isValid, pass.isValid)
            }
                .map { it.first && it.second }
                .map { ProgressButtonState.Enable(it) }
                .collect { buttonShareFlow.emit(it) }
        }
    }


    fun onValueChange(input: String?, type: LoginFieldType) {
        when (type) {
            LoginFieldType.USER_NAME_EDIT_TEXT -> userNameState.value = LoginFieldState(input)
            LoginFieldType.PASSWORD_EDIT_TEXT -> passwordState.value = LoginFieldState(input)
        }
    }

    fun click(){
        viewModelScope.launch {
            buttonShareFlow.emit(ProgressButtonState.Loading())
            delay(2000)
            buttonShareFlow.emit(ProgressButtonState.Enable(true))
        }
    }


}