package com.example.network.repository

import com.example.network_api.ErrorType
import com.example.network_api.RespMapper
import com.example.network_api.api.SquadApi
import com.example.network_api.entity.Users
import com.example.network_api.repository.SquadRepository
import com.example.network_api.response.DefaultApiResponse
import com.example.network_api.response.RespResult
import com.example.network_api.response.SquadResponse
import retrofit2.http.Path
import javax.inject.Inject

class SquadRepositoryImpl @Inject constructor(
    private val squadApi: SquadApi
) : SquadRepository {
    override suspend fun saveSquad(
        teamId: Long,
        scheduleId: Long,
        users: Users
    ): RespResult<DefaultApiResponse> {
        val result = squadApi.saveSquad(teamId, scheduleId, users)
        return if (result.isSuccessful) {
            RespResult.Success(result.body()!!)
        } else {
            val errorBodyJson = result.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }

    override suspend fun loadSquad(
        teamId: Long,
        scheduleId: Long,
    ): RespResult<SquadResponse> {
        val result = squadApi.loadSquad(teamId, scheduleId)
        return if (result.isSuccessful) {
            RespResult.Success(result.body()!!)
        } else {
            val errorBodyJson = result.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }
}