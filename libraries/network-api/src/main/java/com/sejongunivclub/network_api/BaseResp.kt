package com.sejongunivclub.network_api

import com.google.gson.annotations.SerializedName

data class BaseResp(
    @SerializedName("status")
    val status: Int,

    @SerializedName("code")
    val code: String?,

    @SerializedName("message")
    val message: String?
)
