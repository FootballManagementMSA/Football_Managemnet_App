package com.sejongunivclub.network_api.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val status: Int,
    val message: String,
    @SerializedName("data")
    val tokenData: Token
)
data class Token(
    val userId: Long,
    val accessToken: String,
    val refreshToken: String
)