package com.sejongunivclub.core.model

data class Club(
    val status: Int,
    val message: String,
    val data: List<ClubInfo>,
)

data class ClubInfo(
    val teamId: Int,
    val teamName: String?,
    val totalMemberCnt: Int,
    val details: String?,
    val uniqueNum: String,
    val emblem: String
)
