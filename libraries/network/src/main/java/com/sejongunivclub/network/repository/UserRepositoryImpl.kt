package com.sejongunivclub.network.repository

import com.sejongunivclub.network_api.ErrorType
import com.sejongunivclub.network_api.RespMapper
import com.sejongunivclub.network_api.api.ClubApi
import com.sejongunivclub.network_api.api.FootballManagerApi
import com.sejongunivclub.network_api.entity.ClubJoin
import com.sejongunivclub.network_api.entity.Join
import com.sejongunivclub.network_api.entity.Login
import com.sejongunivclub.network_api.repository.UserRepository
import com.sejongunivclub.network_api.response.ClubJoinResponse
import com.sejongunivclub.network_api.response.JoinResponse
import com.sejongunivclub.network_api.response.LoginResponse
import com.sejongunivclub.network_api.response.ModifyUserInfoResponse
import com.sejongunivclub.network_api.response.RespResult
import com.sejongunivclub.network_api.response.SignOutResponse
import com.sejongunivclub.network_api.response.UserInfoResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val footballManagerApi: FootballManagerApi,
    private val clubApi: ClubApi,
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

    override suspend fun signOut(): RespResult<SignOutResponse> {
        val response = footballManagerApi.signOut()
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
        }
    }

    override suspend fun clubJoinRequest(teamId: Long, clubJoinReq: ClubJoin): RespResult<ClubJoinResponse> {
        val response = clubApi.clubJoin(teamId,clubJoinReq)

        return if (response.isSuccessful) {
            RespResult.Success(response.body()!!)
        } else {
            val errorBodyJson = response.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }
}