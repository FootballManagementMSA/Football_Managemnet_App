package com.sejongunivclub.network_api.entity

data class PositionPreset(
    val screenSize: RemoteScreen = RemoteScreen(0.0, 0.0),
    val members: List<Member>
)
