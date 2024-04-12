package com.example.core.model

data class Users(
    val users: List<User>
) {
    companion object{
        fun Users.toEntity() = com.example.network_api.entity.Users(users.mapIndexed { index, user ->
            com.example.network_api.entity.User(
                index.toLong(),
                user.xCoordinate,
                user.yCoordinate
            )
        })
    }
}