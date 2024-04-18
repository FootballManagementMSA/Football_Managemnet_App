package com.sejongunivclub.network_api.response

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    val status: Int,
    val message: String,
    @SerializedName("data")
    val userData: UserInfo
)

data class UserInfo(
    val studentId: String,
    val name: String,
    val image: String?
)
