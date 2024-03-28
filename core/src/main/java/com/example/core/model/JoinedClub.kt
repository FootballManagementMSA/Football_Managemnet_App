package com.example.core.model

import java.time.LocalDateTime

data class JoinedClub(
    val status: Int,
    val message: String,
    val data: List<UserTeamInfoModel>,
)
data class UserTeamInfoModel(
    val role: String,
    val introduce: String,
    val teamName: String,
    val unique_num: String,
    val teamEmblem: String,
    val createdAt: LocalDateTime,
    val sizeOfUsers: Int
)
