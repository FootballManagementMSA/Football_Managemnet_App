package com.sejongunivclub.network.repository

import com.sejongunivclub.network_api.ErrorType
import com.sejongunivclub.network_api.RespMapper
import com.sejongunivclub.network_api.api.ClubApi
import com.sejongunivclub.network_api.api.NaverApi
import com.sejongunivclub.network_api.entity.ClubSchedule
import com.sejongunivclub.network_api.repository.ClubRepository
import com.sejongunivclub.network_api.response.JoinedClubResponse
import com.sejongunivclub.network_api.response.MakeClubResponse
import com.sejongunivclub.network_api.response.DefaultApiResponse
import com.sejongunivclub.network_api.response.MapResponse
import com.sejongunivclub.network_api.response.RespResult
import com.sejongunivclub.network_api.response.SearchClubResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

internal class ClubRepositoryImpl @Inject constructor(
    private val clubApi: ClubApi,
    private val naverApi: NaverApi

) : ClubRepository {
    override suspend fun sendClubInfo(
        name: RequestBody,
        details: RequestBody,
        emblem: MultipartBody.Part
    ): RespResult<MakeClubResponse> {
        val response = clubApi.sendClubInfo(name = name, details = details ,emblem= emblem)
        return if (response.isSuccessful) {
            RespResult.Success(response.body()!!)
        } else {
            val errorBodyJson = response.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }



    override suspend fun createClubSchedule(
        teamId: Long,
        clubSchedule: ClubSchedule
    ): RespResult<DefaultApiResponse> {
        val response = clubApi.makeClubSchedule(teamId, clubSchedule)
        return if (response.isSuccessful) {
            RespResult.Success(response.body()!!)
        } else {
            val errorBodyJson = response.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }

    override suspend fun getJoinedClub(userId: Long): RespResult<JoinedClubResponse> {
        val response = clubApi.getJoinedClub(userId)
        return if (response.isSuccessful) {
            RespResult.Success(response.body()!!)
        } else {
            val errorBodyJson = response.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }


    override suspend fun searchClub(code: String): RespResult<SearchClubResponse> {
        val response = clubApi.searchClub(code)
        return if (response.isSuccessful) {
            RespResult.Success(response.body()!!)
        } else {
            val errorBodyJson = response.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }
    override suspend fun searchMap(code: String): RespResult<MapResponse> {
        val response=naverApi.mapSearch(code,5,"kmIfAtHJWQl3bb81YVzn","4JiJPll3QL")
        return if (response.isSuccessful) {
            RespResult.Success(response.body()!!)
        } else {
            val errorBodyJson = response.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }


    }
}