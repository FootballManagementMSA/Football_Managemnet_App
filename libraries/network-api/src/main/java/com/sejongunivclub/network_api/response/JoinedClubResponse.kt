package com.sejongunivclub.network_api.response

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
    val createdAt: String,
    val sizeOfUsers: Int
)
