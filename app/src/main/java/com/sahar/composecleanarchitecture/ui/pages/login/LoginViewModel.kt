package com.sahar.composecleanarchitecture.ui.pages.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sahar.composecleanarchitecture.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import javax.inject.Inject



@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository
) : ViewModel() {

    private val userNameState = MutableLiveData<LoginFieldState>()
    val userNameLiveData get():LiveData<LoginFieldState> = userNameState

    private val passwordState = MutableLiveData<LoginFieldState>()
    val passwordLiveData get():LiveData<LoginFieldState> = passwordState


    fun onValueChange(input:String?,type:LoginFieldType){
        when(type){
            LoginFieldType.USER_NAME_EDIT_TEXT -> userNameState.value = LoginFieldState(input)
            LoginFieldType.PASSWORD_EDIT_TEXT -> passwordState.value = LoginFieldState(input)
        }
    }


}