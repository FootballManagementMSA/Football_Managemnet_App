package com.example.core.datasource

import android.util.Log
import com.example.core.ResultState.BaseResult
import com.example.core.ResultState.ClubJoinRequestResult
import com.example.core.ResultState.LoginResult
import com.example.core.ResultState.JoinResult
import com.example.core.mapper.EntityMapper.mapToEntity
import com.example.core.mapper.UiModelMapper.mapToUiModel
import com.example.core.model.ClubJoinRequestModel
import com.example.core.model.JoinModel
import com.example.core.model.LoginModel
import com.example.core.model.ModifyUserInfoModel
import com.example.core.model.MyPageUserInfoUiModel
import com.example.core.model.UserInfo
import com.example.core.util.FormDataUtil.mapToMultipart
import com.example.core.util.FormDataUtil.mapToRequestBody
import com.example.network_api.repository.UserRepository
import com.example.network_api.response.RespResult
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class UserRemoteDataSourceImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val userLocalDataSource: UserLocalDataSource
) : UserRemoteDataSource {


    override suspend fun join(joinModel: JoinModel): JoinResult {
        val result=userRepository.join(joinModel.mapToEntity())
        return when(result){
            is RespResult.Success ->{
                JoinResult.Success(result.data.message)

            }
            is RespResult.Error ->{
                JoinResult.Error(result.error.errorMessage)

            }
        }


    }

    override suspend fun clubJoinRequest(clubJoinRequestModel: ClubJoinRequestModel): ClubJoinRequestResult {

        val result=userRepository.clubJoinRequest(clubJoinRequestModel.mapToEntity())

        return when(result){
            is RespResult.Success ->{
                ClubJoinRequestResult.Success(result.data.message)

            }
            is RespResult.Error ->{
                ClubJoinRequestResult.Error(result.error.errorMessage)

            }
        }

    }


    override suspend fun login(loginModel: LoginModel): LoginResult {
        val result = userRepository.login(loginModel.mapToEntity())
        return when (result) {
            is RespResult.Success -> {
                // 로그인 성공 시 Firebase 토큰 가져오기
                val firebaseToken = FirebaseMessaging.getInstance().token.await()

                with(userLocalDataSource) {
                    saveAccessToken(result.data.tokenData.accessToken)
                    saveAccessToken(result.data.tokenData.refreshToken)
                    saveAccount(loginModel.id)
                    savePassword(loginModel.pw)
                    saveUserId(result.data.tokenData.userId)
                    Log.d("UserAcessToken",result.data.tokenData.accessToken)
                    Log.d("UserId",result.data.tokenData.userId.toString())

                    // Firebase 토큰 저장
                    saveFcmToken(firebaseToken)
                    Log.d("UserFcm", firebaseToken.toString())
                }

                LoginResult.Success(
                    result.data.tokenData.accessToken,
                    result.data.tokenData.refreshToken
                )
            }

            is RespResult.Error -> {
                LoginResult.Error(result.error.errorMessage)
            }
        }
    }
    override suspend fun sendClubInfoData() {
    }

    //레포지토리에서 가져오도록 수정
    override suspend fun getJoinClubUserInfo(): List<UserInfo> {
        return listOf(
            UserInfo("임성우", "133333", "3", "st", "erer"),
            UserInfo("테스트", "26", "5", "GK", "안녕하세요 테스트입니다.")
        )
    }

    override suspend fun getUserInfo(): MyPageUserInfoUiModel {
        return userRepository.getUserInfo().mapToUiModel()
    }

    override suspend fun modifyUserInfo(modifyUserInfoModel: ModifyUserInfoModel): BaseResult {
        val requestName = mapToRequestBody(modifyUserInfoModel.name)
        val requestAge = mapToRequestBody(modifyUserInfoModel.age.toString())
        val requestHeight = mapToRequestBody(modifyUserInfoModel.height.toString())
        val requestSex = mapToRequestBody(modifyUserInfoModel.sex)
        val requestPosition = mapToRequestBody(modifyUserInfoModel.position)
        val requestFoot = mapToRequestBody(modifyUserInfoModel.foot)
        val result = userRepository.modifyUserInfo(
            requestName,
            requestAge,
            requestHeight,
            requestSex,
            requestPosition,
            requestFoot,
            mapToMultipart("image", modifyUserInfoModel.image)
        )
        return when (result) {
            is RespResult.Success -> {
                BaseResult.Success
            }

            is RespResult.Error -> {
                Log.e("tt", "${result.error.errorMessage} ${result.error.code}")
                BaseResult.Error(result.error.errorMessage)
            }
        }
    }


}
