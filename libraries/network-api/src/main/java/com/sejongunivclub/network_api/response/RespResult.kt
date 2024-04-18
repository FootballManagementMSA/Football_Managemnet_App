package com.sejongunivclub.network_api.response

import com.sejongunivclub.network_api.ErrorType

sealed class RespResult<T> {
    data class Success<T>(val data: T) : RespResult<T>()
    data class Error<T>(val error: ErrorType) : RespResult<T>()
}
