package com.example.core.model

data class JoinedClubInfo(
    val status: Int,
    val code: String,
    val message: String,
    val data: List<JoinedClubData>
)

data class JoinedClubData(
    val id: Long,
    val uniqueNum: String
)
