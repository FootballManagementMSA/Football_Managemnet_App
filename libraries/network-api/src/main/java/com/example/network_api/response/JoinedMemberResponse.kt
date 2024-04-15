package com.example.network_api.response

data class JoinedMemberResponse(
    val status: Int,
    val code: String,
    val message: String,
    val data: List<User>
)

data class User(
    val userId: Long,
    val userName: String,
    val position: String,
    val age: Int,
    val teamCnt: Int
)