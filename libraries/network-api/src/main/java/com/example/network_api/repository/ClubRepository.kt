package com.example.network_api.repository

import com.example.network_api.entity.ClubSchedule
import com.example.network_api.response.JoinedClubResponse
import com.example.network_api.response.MakeClubResponse
import com.example.network_api.response.MakeClubScheduleResponse
import com.example.network_api.response.MapResponse
import com.example.network_api.response.RespResult
import com.example.network_api.response.SearchClubResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ClubRepository {
    suspend fun sendClubInfo(name: RequestBody, details: RequestBody, emblem: MultipartBody.Part): RespResult<MakeClubResponse>
    suspend fun searchClub(code: String): RespResult<SearchClubResponse>
    suspend fun createClubSchedule(teamId: Long, clubSchedule: ClubSchedule) : RespResult<MakeClubScheduleResponse>
    suspend fun getJoinedClub(userId: Long): RespResult<JoinedClubResponse>

   suspend fun searchMap(code:String):RespResult<MapResponse>

}