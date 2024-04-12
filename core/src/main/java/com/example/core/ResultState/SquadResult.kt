package com.example.core.ResultState

import com.example.network_api.entity.User
import com.example.network_api.response.UserData

sealed class SquadResult {
    data class Error(val errorMessage: String) : SquadResult()
    data class Success<T>(val data: T) : SquadResult()

}