package com.example.network_api.repository

import com.example.network_api.response.JoinedClubResponse
import com.example.network_api.response.MakeClubResponse
import com.example.network_api.response.RespResult
import com.example.network_api.response.SearchClubResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ClubRepository {
    suspend fun sendClubInfo(name: RequestBody, emblem: MultipartBody.Part): RespResult<MakeClubResponse>
    suspend fun searchClub(code: String): RespResult<SearchClubResponse>
    suspend fun getJoinedClub(): RespResult<JoinedClubResponse>
}