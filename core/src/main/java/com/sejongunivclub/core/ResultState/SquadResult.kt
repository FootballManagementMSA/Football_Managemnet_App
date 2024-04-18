package com.sejongunivclub.core.ResultState

sealed class SquadResult {
    data class Error(val errorMessage: String) : SquadResult()
    data class Success<T>(val data: T) : SquadResult()

}