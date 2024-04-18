package com.sejongunivclub.network_api.entity

data class Member(
    val id: String,
    val name: String,
    val number: String,
    val role: String,
    val position: Position
)
