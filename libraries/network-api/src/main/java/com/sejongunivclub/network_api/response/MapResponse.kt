package com.sejongunivclub.network_api.response

data class MapResponse(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<LocationInfoResponse>
)

data class LocationInfoResponse(
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