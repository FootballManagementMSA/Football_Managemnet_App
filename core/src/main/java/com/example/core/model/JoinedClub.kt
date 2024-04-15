package com.example.core.model

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
    val createdAt: String,
    val sizeOfUsers: Int
)

