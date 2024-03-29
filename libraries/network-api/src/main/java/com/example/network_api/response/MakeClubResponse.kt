package com.example.network_api.response

import com.google.gson.annotations.SerializedName

data class MakeClubResponse(
    val status: Int,
    val message: String,
    @SerializedName("data")
    val unique: UniqueNumberData
)

data class UniqueNumberData(
   val uniqueNumber: String
)

data class MakeClubScheduleResponse(
    val status: Int,
    val message: String,
    val code: String
)