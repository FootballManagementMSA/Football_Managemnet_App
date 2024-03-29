package com.example.network_api.response

data class SearchClubResponse(
    val status: Int,
    val message: String,
    val data: List<ClubInfoResponse>,
)

data class ClubInfoResponse(
    val teamId: Int,
    val teamName: String?,
    val totalMemberCnt: Int,
    val details: String?,
    val uniqueNum: String,
    val emblem: String
)
