package com.example.core.datasource

import com.example.core.ResultState.JoinResult
import com.example.core.ResultState.BaseResult
import com.example.core.ResultState.ClubJoinRequestResult
import com.example.core.ResultState.LoginResult
import com.example.core.model.ClubJoinRequestModel
import com.example.core.model.JoinModel
import com.example.core.model.LoginModel
import com.example.core.model.ModifyUserInfoModel
import com.example.core.model.MyPageUserInfoUiModel
import com.example.core.model.UserInfo

interface UserRemoteDataSource {
    suspend fun login(loginModel: LoginModel): LoginResult
    suspend fun sendClubInfoData()
    suspend fun getJoinClubUserInfo(): List<UserInfo>


    suspend fun join(joinModel: JoinModel): JoinResult

    suspend fun clubJoinRequest(clubJoinRequestModel: ClubJoinRequestModel):ClubJoinRequestResult




    suspend fun getUserInfo(): MyPageUserInfoUiModel
    suspend fun modifyUserInfo(modifyUserInfoModel: ModifyUserInfoModel): BaseResult
}