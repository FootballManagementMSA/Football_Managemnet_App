package com.sejongunivclub.network_api.api

import com.sejongunivclub.network_api.entity.Users
import com.sejongunivclub.network_api.response.DefaultApiResponse
import com.sejongunivclub.network_api.response.SquadResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface SquadApi {
    @GET("/api/team-service/schedules/{scheduleId}/teams/{teamId}/squad")
    fun saveSquad(
        @Path("teamId") teamId: Long,
        @Path("scheduleId") scheduleId: Long,
        @Body users: Users
    ): Response<DefaultApiResponse>

    @GET("/api/team-service/schedules/{scheduleId}/teams/{teamId}/squad")
    fun loadSquad(
        @Path("teamId") teamId: Long,
        @Path("scheduleId") scheduleId: Long,
    ): Response<SquadResponse>
}