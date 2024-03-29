package com.example.network.repository

import com.example.network_api.ErrorType
import com.example.network_api.RespMapper
import com.example.network_api.api.ClubApi
import com.example.network_api.api.FootballManagerApi
import com.example.network_api.entity.ClubJoin
import com.example.network_api.entity.Join
import com.example.network_api.entity.Login
import com.example.network_api.repository.UserRepository
import com.example.network_api.response.ClubJoinResponse
import com.example.network_api.response.JoinResponse
import com.example.network_api.response.LoginResponse
import com.example.network_api.response.ModifyUserInfoResponse
import com.example.network_api.response.RespResult
import com.example.network_api.response.UserInfoResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val footballManagerApi: FootballManagerApi,
    private val clubApi: ClubApi
) : UserRepository {
    override suspend fun login(loginReq: Login): RespResult<LoginResponse> {
        val response = footballManagerApi.login(loginReq)

        return if (response.isSuccessful) {
            RespResult.Success(response.body()!!)
        } else {
            val errorBodyJson = response.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }

    override suspend fun getUserInfo(): RespResult<UserInfoResponse> {
        val response = footballManagerApi.getUserInfo()

        return if (response.isSuccessful) {
            RespResult.Success(response.body()!!)
        } else {
            val errorBodyJson = response.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }

    override suspend fun modifyUserInfo(
        name: RequestBody,
        age: RequestBody,
        height: RequestBody,
        sex: RequestBody,
        position: RequestBody,
        foot: RequestBody,
        image: MultipartBody.Part
    ): RespResult<ModifyUserInfoResponse> {
        val response = footballManagerApi.modifyUserInfo(
            name = name,
            age = age,
            height = height,
            sex = sex,
            position = position,
            foot = foot,
            image
        )
        return if (response.isSuccessful) {
            RespResult.Success(response.body()!!)
        } else {
            val errorBodyJson = response.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }

    override suspend fun join(joinReq: Join): RespResult<JoinResponse> {
        val response = footballManagerApi.Join(joinReq)

        return if (response.isSuccessful) {
            RespResult.Success(response.body()!!)
        } else {
            val errorBodyJson = response.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }    }

    override suspend fun clubJoinRequest(clubJoinReq: ClubJoin): RespResult<ClubJoinResponse> {
        val response = clubApi.clubJoin(clubJoinReq)

        return if (response.isSuccessful) {
            RespResult.Success(response.body()!!)
        } else {
            val errorBodyJson = response.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }      }
}