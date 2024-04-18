package com.sejongunivclub.feature_join.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sejongunivclub.core.ResultState.JoinResult
import com.sejongunivclub.core.model.JoinModel
import com.sejongunivclub.feature_join.domain.usecase.JoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val joinUseCase: JoinUseCase
) : ViewModel() {


    private val _userId = mutableStateOf("")
    val userId: State<String> = _userId

    private val _userPassword = mutableStateOf("")
    val userPassword: State<String> = _userPassword

    private val _userPosition = mutableStateOf("")
    val userPosition: State<String> = _userPosition

    private val _userFoot = mutableStateOf("")
    val userFoot: State<String> = _userFoot

    private val _userAge = mutableStateOf("")
    val userAge: State<String> = _userAge

    private val _userHeight = mutableStateOf("")
    val userHeight: State<String> = _userHeight

    private val _userGender = mutableStateOf("")
    val userGender: State<String> = _userGender

    private val _userName = mutableStateOf("")
    val userName: State<String> = _userName


    private val _selectedInfo = mutableStateOf("" to "")
    val selectedInfo: State<Pair<String, String>> get() = _selectedInfo


    private val _JoinResult = MutableSharedFlow<JoinResult>(replay = 1)
    val JoinResult: SharedFlow<JoinResult> = _JoinResult.asSharedFlow()

    //추후 백엔드 재학생 인증 api 생성시 연결작업을 위한 주석처리
/*
    private val _loginResult = MutableSharedFlow<LoginResult>(replay = 1)
    val loginResult: SharedFlow<LoginResult> = _loginResult.asSharedFlow()



    fun login() {
        viewModelScope.launch {
            _loginResult.emit(LoginResult.Loading)
            val result = JoinUseCase(LoginModel(_userId.value, _userPassword.value))
            _loginResult.emit(result)
        }
    }
    */


    fun join() {
        viewModelScope.launch {

            Log.d("test_joinViewModel_join", "joinviewModel_join_function_test")


            val result = joinUseCase(
                JoinModel(
                    _userId.value,
                    _userPassword.value,
                    _userPosition.value,
                    _userFoot.value,
                    _userGender.value,
                    _userAge.value.toInt(),
                    _userHeight.value.toInt()
                )
            )
            _JoinResult.emit(result)
        }
    }


    fun updateUserId(userId: String) {
        _userId.value = userId
    }

    fun updateUserPassword(userPassword: String) {
        _userPassword.value = userPassword
    }



    fun updateSelectedInfo(position: String = "", foot: String = "") {
        _selectedInfo.value = position to foot
        if (position.isNotEmpty()) {
            _userPosition.value = position
        }
        if (foot.isNotEmpty()) {
            _userFoot.value = foot
        }
    }

    fun updateUserAge(age: String) {
        _userAge.value = age
    }

    fun updateUserHeight(height: String) {
        _userHeight.value = height
    }

    fun updateUserGender(gender: String) {
        _userGender.value = gender
    }

    fun updateUserName(name: String) {
        _userName.value = name
    }


}