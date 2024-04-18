package com.sejongunivclub.network_api.api

import com.sejongunivclub.network_api.response.JoinedClubInfoResponse
import com.sejongunivclub.network_api.response.MainHomeScheduleResponse
import com.sejongunivclub.network_api.response.MainHomeStudentDataResponse
import retrofit2.Response
import retrofit2.http.GET

interface MainHomeApi {

    @GET("/api/user-service/student")
    suspend fun loadStudentData(): Response<MainHomeStudentDataResponse>

    @GET("/api/user-service/schedule")
    suspend fun loadSchedule(): Response<MainHomeScheduleResponse>

    @GET("/api/user-service/team")
    suspend fun getJoinedClubInfo(): Response<JoinedClubInfoResponse>
}