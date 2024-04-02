package com.example.network_api.api

import com.example.network_api.response.MapResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverApi {
    @GET("v1/search/local.json")
    suspend fun mapSearch(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String
    ): Response<MapResponse>
}