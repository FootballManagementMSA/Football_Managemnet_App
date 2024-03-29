package com.example.core.ResultState

sealed class ClubJoinRequestResult {
    data class Error(val errorMessage: String) : ClubJoinRequestResult()
    data class Success(val some:String) : ClubJoinRequestResult()
}