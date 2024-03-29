package com.example.core.model

data class ClubSchedule(
    val title: String,
    val memo: String,
    val startTime: java.time.LocalDateTime,
    val endTime: java.time.LocalDateTime,
    val place: String,
    val awayTeamId: Long,
    val longitude: Double,
    val latitude: Double
) {
    companion object {
        fun ClubSchedule.mapToEntity() = com.example.network_api.entity.ClubSchedule(
            this.title,
            this.memo,
            this.startTime,
            this.endTime,
            this.place,
            this.awayTeamId,
            this.longitude,
            this.latitude
        )
    }
}
