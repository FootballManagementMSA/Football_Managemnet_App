package com.example.network_api.repository

import com.example.network_api.entity.ClubJoin
import com.example.network_api.entity.Join
import com.example.network_api.entity.Login
import com.example.network_api.response.ClubJoinResponse
import com.example.network_api.response.JoinResponse
import com.example.network_api.response.LoginResponse
import com.example.network_api.response.ModifyUserInfoResponse
import com.example.network_api.response.RespResult
import com.example.network_api.response.UserInfoResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface UserRepository {
    suspend fun login(loginReq: Login): RespResult<LoginResponse>
    suspend fun join(joinReq: Join): RespResult<JoinResponse>

    suspend fun clubJoinRequest(clubJoinReq: ClubJoin):RespResult<ClubJoinResponse>


    suspend fun getUserInfo(): RespResult<UserInfoResponse>
    suspend fun modifyUserInfo(
        name: RequestBody,
        age: RequestBody,
        height: RequestBody,
        sex: RequestBody,
        position: RequestBody,
        foot: RequestBody,
        image: MultipartBody.Part
    ): RespResult<ModifyUserInfoResponse>
}