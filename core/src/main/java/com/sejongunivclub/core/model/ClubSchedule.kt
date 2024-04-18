package com.sejongunivclub.core.model

data class ClubSchedule(
    val title: String,
    val memo: String,
    val startTime: String,
    val endTime: String,
    val place: String,
    val awayTeamId: Long,
    val longitude: Double,
    val latitude: Double
) {
    companion object {
        fun ClubSchedule.mapToEntity() = com.sejongunivclub.network_api.entity.ClubSchedule(
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
