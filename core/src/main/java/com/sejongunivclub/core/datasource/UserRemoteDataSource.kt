package com.sejongunivclub.core.datasource

import com.sejongunivclub.core.ResultState.BaseResult
import com.sejongunivclub.core.ResultState.ClubJoinRequestResult
import com.sejongunivclub.core.ResultState.JoinResult
import com.sejongunivclub.core.ResultState.LoginResult
import com.sejongunivclub.core.model.ClubJoinRequestModel
import com.sejongunivclub.core.model.JoinModel
import com.sejongunivclub.core.model.LoginModel
import com.sejongunivclub.core.model.ModifyUserInfoModel
import com.sejongunivclub.core.model.MyPageUserInfoUiModel
import com.sejongunivclub.core.model.SignOut
import com.sejongunivclub.core.model.UserInfo

interface UserRemoteDataSource {
    suspend fun login(loginModel: LoginModel): LoginResult
    suspend fun sendClubInfoData()
    suspend fun getJoinClubUserInfo(): List<UserInfo>
    suspend fun join(joinModel: JoinModel): JoinResult
    suspend fun clubJoinRequest(teamId: Long, clubJoinRequestModel: ClubJoinRequestModel):ClubJoinRequestResult
    suspend fun getUserInfo(): MyPageUserInfoUiModel
    suspend fun modifyUserInfo(modifyUserInfoModel: ModifyUserInfoModel): BaseResult
    suspend fun signOut(): SignOut
}