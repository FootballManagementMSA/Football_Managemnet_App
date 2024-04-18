package com.sejongunivclub.feature_login.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sejongunivclub.core.ResultState.LoginResult
import com.sejongunivclub.core.model.LoginModel
import com.sejongunivclub.feature_login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _userId = mutableStateOf("")
    val userId: State<String> = _userId

    private val _userPassword = mutableStateOf("")
    val userPassword: State<String> = _userPassword

    private val _loginResult = MutableSharedFlow<LoginResult>(replay = 1)
    val loginResult: SharedFlow<LoginResult> = _loginResult.asSharedFlow()

    fun login() {
        viewModelScope.launch {
            _loginResult.emit(LoginResult.Loading)
            val result = loginUseCase(LoginModel(_userId.value, _userPassword.value))
            _loginResult.emit(result)
        }
    }
    fun updateUserId(userId: String) {
        _userId.value = userId
    }

    fun updateUserPassword(userPassword: String) {
        _userPassword.value = userPassword
    }

}