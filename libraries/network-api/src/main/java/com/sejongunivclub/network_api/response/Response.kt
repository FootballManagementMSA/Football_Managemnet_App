package com.sejongunivclub.network_api.response

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class MainHomeStudentDataResponse(
    val status: Int,
    val code: String,
    val message: String,
    val data: Student
)

data class Student(
    val name: String,
    val game: Int,
    val goal: Int,
    val position: String,
    val foot: String,
    val image: String,
    val age: Int
)

data class MainHomeScheduleResponse(
    val status: Int,
    val code: String,
    val message: String,
    val data: List<MainScheduleResponse?>
)

data class MainScheduleResponse(
    val place: String,
    val startTime: LocalDateTime,
    @SerializedName("HomeTeam") val homeTeam: TeamResponse,
    @SerializedName("AwayTeam") val awayTeam: TeamResponse
)

data class TeamResponse(
    val name: String,
    val emblem: String
)