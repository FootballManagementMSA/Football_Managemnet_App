package com.example.core.model

data class UserData(
    val users: List<User>
)

data class User(
    val name: String,
    val image: String,
    val xCoordinate: Double,
    val yCoordinate: Double
)