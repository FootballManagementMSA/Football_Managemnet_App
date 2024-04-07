package com.example.network.repository

import com.example.network_api.ErrorType
import com.example.network_api.RespMapper
import com.example.network_api.api.SquadApi
import com.example.network_api.entity.Users
import com.example.network_api.repository.SquadRepository
import com.example.network_api.response.DefaultApiResponse
import com.example.network_api.response.RespResult
import com.example.network_api.response.SquadResponse

class SquadRepositoryImpl(
    private val squadApi: SquadApi
) : SquadRepository {
    override fun saveSquad(users: Users): RespResult<DefaultApiResponse> {
        val result = squadApi.saveSquad(users)
        return if(result.isSuccessful){
            RespResult.Success(result.body()!!)
        } else {
            val errorBodyJson = result.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }

    override fun loadSquad(): RespResult<SquadResponse> {
        val result = squadApi.loadSquad()
        return if(result.isSuccessful){
            RespResult.Success(result.body()!!)
        } else {
            val errorBodyJson = result.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }
}