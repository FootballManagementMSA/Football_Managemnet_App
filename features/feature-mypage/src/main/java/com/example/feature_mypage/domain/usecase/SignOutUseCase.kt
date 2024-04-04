package com.example.feature_mypage.domain.usecase

import com.example.core.datasource.UserRemoteDataSource
import com.example.core.model.SignOut
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend operator fun invoke(): SignOut {
      return userRemoteDataSource.signOut()
    }
}