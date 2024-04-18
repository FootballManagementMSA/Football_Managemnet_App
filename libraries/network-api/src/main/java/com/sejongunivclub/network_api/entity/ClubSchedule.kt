package com.sejongunivclub.network_api.entity

data class ClubSchedule(
    val title: String,
    val memo: String,
    val startTime: String,
    val endTime: String,
    val place: String,
    val awayTeamId: Long,
    val longitude: Double,
    val latitude: Double
)
