package com.sejongunivclub.network_api.repository

import com.sejongunivclub.network_api.ErrorType
import com.sejongunivclub.network_api.RespMapper
import com.sejongunivclub.network_api.api.MainHomeApi
import com.sejongunivclub.network_api.response.JoinedClubInfoResponse
import com.sejongunivclub.network_api.response.MainHomeScheduleResponse
import com.sejongunivclub.network_api.response.MainHomeStudentDataResponse
import com.sejongunivclub.network_api.response.RespResult
import javax.inject.Inject

class MainHomeRepositoryImpl @Inject constructor(
    private val mainHomeApi: MainHomeApi
) : MainHomeRepository {
    override suspend fun loadData(): RespResult<MainHomeStudentDataResponse> {
        val result = mainHomeApi.loadStudentData()
        return if (result.isSuccessful) {
            RespResult.Success(result.body()!!)
        } else {
            val errorBodyJson = result.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }

    override suspend fun loadSchedule(): RespResult<MainHomeScheduleResponse> {
        val result = mainHomeApi.loadSchedule()
        return if (result.isSuccessful) {
            RespResult.Success(result.body()!!)
        } else {
            val errorBodyJson = result.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }

    override suspend fun getJoinedClubInfo(): RespResult<JoinedClubInfoResponse> {
        val result = mainHomeApi.getJoinedClubInfo()
        return if (result.isSuccessful) {
            RespResult.Success(result.body()!!)
        } else {
            val errorBodyJson = result.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }
}