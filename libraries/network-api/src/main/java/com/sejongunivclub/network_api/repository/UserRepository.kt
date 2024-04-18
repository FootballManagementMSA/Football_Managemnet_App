package com.sejongunivclub.network_api.repository

import com.sejongunivclub.network_api.entity.ClubJoin
import com.sejongunivclub.network_api.entity.Join
import com.sejongunivclub.network_api.entity.Login
import com.sejongunivclub.network_api.response.ClubJoinResponse
import com.sejongunivclub.network_api.response.JoinResponse
import com.sejongunivclub.network_api.response.LoginResponse
import com.sejongunivclub.network_api.response.ModifyUserInfoResponse
import com.sejongunivclub.network_api.response.RespResult
import com.sejongunivclub.network_api.response.SignOutResponse
import com.sejongunivclub.network_api.response.UserInfoResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface UserRepository {
    suspend fun login(loginReq: Login): RespResult<LoginResponse>
    suspend fun join(joinReq: Join): RespResult<JoinResponse>
    suspend fun clubJoinRequest(teamId: Long, clubJoinReq: ClubJoin):RespResult<ClubJoinResponse>
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
    suspend fun signOut(): RespResult<SignOutResponse>
}