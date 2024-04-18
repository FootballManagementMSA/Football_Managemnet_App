package com.sejongunivclub.feature_mypage.domain.usecase

import com.sejongunivclub.core.ResultState.BaseResult
import com.sejongunivclub.core.datasource.UserRemoteDataSource
import com.sejongunivclub.core.model.ModifyUserInfoModel
import javax.inject.Inject

class ModifyUserInfoUseCase @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend operator fun invoke(modifyUserInfoModel: ModifyUserInfoModel): BaseResult {
        return userRemoteDataSource.modifyUserInfo(modifyUserInfoModel)
    }
}