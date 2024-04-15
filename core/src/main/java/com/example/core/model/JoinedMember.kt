package com.example.core.model

data class JoinedMember(
    val status: Int,
    val code: String,
    val message: String,
    val data: List<UserModel>
)

data class UserModel(
    val userId: Long,
    val userName: String,
    val position: String,
    val age: Int,
    val teamCnt: Int
)