package com.sejongunivclub.network_api.response

data class SquadResponse(
    val status: Int,
    val code: String,
    val message: String,
    val data: UserData
)

data class UserData(
    val users: List<User2>
)

data class User2(
    val name: String,
    val image: String,
    val xCoordinate: Double,
    val yCoordinate: Double
)