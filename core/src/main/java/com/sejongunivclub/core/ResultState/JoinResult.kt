package com.sejongunivclub.core.ResultState

sealed class JoinResult {
    data class Error(val errorMessage: String) : JoinResult()
    data class Success(val some:String) : JoinResult()

}
