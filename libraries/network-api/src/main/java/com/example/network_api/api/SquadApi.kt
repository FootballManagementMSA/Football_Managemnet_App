package com.example.network_api.api

import com.example.network_api.entity.Users
import com.example.network_api.response.DefaultApiResponse
import com.example.network_api.response.SquadResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface SquadApi {
    @GET("/api/team-service/schedules/{scheduleId}/teams/{teamId}/squad")
    fun saveSquad(
        @Body users: Users
    ): Response<DefaultApiResponse>

    @GET("/api/team-service/schedules/{scheduleId}/teams/{teamId}/squad")
    fun loadSquad(): Response<SquadResponse>
}