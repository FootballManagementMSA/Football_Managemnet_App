package com.example.network_api.response

data class JoinedClubInfoResponse(
    val status: Int,
    val code: String,
    val message: String,
    val data: List<JoinedClubDataResponse>
)

data class JoinedClubDataResponse(
    val id: Long,
    val uniqueNum: String
)
