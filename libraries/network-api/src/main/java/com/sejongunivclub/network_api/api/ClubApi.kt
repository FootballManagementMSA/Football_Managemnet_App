package com.sejongunivclub.network_api.api

import com.sejongunivclub.network_api.entity.ClubJoin
import com.sejongunivclub.network_api.entity.ClubSchedule
import com.sejongunivclub.network_api.response.ClubJoinResponse
import com.sejongunivclub.network_api.response.DefaultApiResponse
import com.sejongunivclub.network_api.response.JoinedClubResponse
import com.sejongunivclub.network_api.response.MakeClubResponse
import com.sejongunivclub.network_api.response.SearchClubResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ClubApi {
    @Multipart
    @POST("/api/team-service/teams")
    suspend fun sendClubInfo(
        @Part("name") name: RequestBody,
        @Part("details") details: RequestBody,
        @Part emblem: MultipartBody.Part
    ): Response<MakeClubResponse>

    @GET("/api/team-service/team/search")
    suspend fun searchClub(
        @Query("search") code: String
    ): Response<SearchClubResponse>

    @POST("/api/team-service/team/{teamId}/apply")
    suspend fun clubJoin(
        @Path("teamId") teamId: Long,
        @Body JoinReq: ClubJoin
    ): Response<ClubJoinResponse>


    @POST("/api/team-service/{teamId}/schedules")
    suspend fun makeClubSchedule(
        @Path("teamId") teamId: Long,
        @Body clubSchedule: ClubSchedule
    ): Response<DefaultApiResponse>

    @GET("/api/team-service/users/{userId}/teams")
    suspend fun getJoinedClub(
        @Path("userId") userId: Long
    ): Response<JoinedClubResponse>


}