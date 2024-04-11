package com.example.core.model

data class UserData(
    val users: List<User>
)

data class User(
    val name: String,
    val image: String,
    val xCoordinate: Double,
    val yCoordinate: Double
) {
    companion object {
        fun User.toEntity(id: Int) = com.example.network_api.entity.User(
            id.toLong(),
            xCoordinate,
            yCoordinate
        )
    }
}