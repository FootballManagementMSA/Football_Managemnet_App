package com.example.network_api.response

import java.time.LocalDateTime

data class JoinedClubResponse(
    val status: Int,
    val message: String,
    val data: List<UserTeamInfo>,
)

data class UserTeamInfo(
    val role: String,
    val introduce: String,
    val teamName: String,
    val unique_num: String,
    val teamEmblem: String,
    val createdAt: LocalDateTime,
    val sizeOfUsers: Int
)
