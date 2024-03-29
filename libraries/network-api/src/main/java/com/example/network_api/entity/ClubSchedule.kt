package com.example.network_api.entity

data class ClubSchedule(
    val title: String,
    val memo: String,
    val startTime: java.time.LocalDateTime,
    val endTime: java.time.LocalDateTime,
    val place: String,
    val awayTeamId: Long,
    val longitude: Double,
    val latitude: Double
)
