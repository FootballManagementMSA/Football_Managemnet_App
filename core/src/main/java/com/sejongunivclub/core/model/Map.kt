package com.sejongunivclub.core.model

data class Map(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<LocationInfo>
)

data class LocationInfo(
    val title: String,
    val link: String,
    val category: String,
    val description: String?,
    val telephone: String?,
    val address: String,
    val roadAddress: String,
    val mapx: String,
    val mapy: String
)
