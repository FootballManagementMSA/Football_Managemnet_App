package com.sejongunivclub.core.ResultState

sealed class LoginResult {
    data class Success(val accessToken: String, val refreshToken: String) : LoginResult()
    data class Error(val errorMessage: String) : LoginResult()
    data object UnInitialize : LoginResult()
    data object Loading : LoginResult()
}