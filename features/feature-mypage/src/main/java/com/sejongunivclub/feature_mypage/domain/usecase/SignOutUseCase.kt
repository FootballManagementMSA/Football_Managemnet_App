package com.sejongunivclub.feature_mypage.domain.usecase

import com.sejongunivclub.core.datasource.UserRemoteDataSource
import com.sejongunivclub.core.model.SignOut
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend operator fun invoke(): SignOut {
      return userRemoteDataSource.signOut()
    }
}