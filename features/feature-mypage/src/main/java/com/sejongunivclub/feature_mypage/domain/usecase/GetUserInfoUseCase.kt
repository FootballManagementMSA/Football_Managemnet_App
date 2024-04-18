package com.sejongunivclub.feature_mypage.domain.usecase

import com.sejongunivclub.core.datasource.UserRemoteDataSource
import com.sejongunivclub.core.model.MyPageUserInfoUiModel
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend operator fun invoke(): MyPageUserInfoUiModel {
        return userRemoteDataSource.getUserInfo()
    }
}