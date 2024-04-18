package com.sejongunivclub.feature_dialog.domain

import com.sejongunivclub.core.datasource.UserRemoteDataSource
import com.sejongunivclub.core.model.UserInfo
import javax.inject.Inject

class GetJoinClubUserInfoUseCase @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend operator fun invoke(): List<UserInfo>{
        return userRemoteDataSource.getJoinClubUserInfo()
    }
}