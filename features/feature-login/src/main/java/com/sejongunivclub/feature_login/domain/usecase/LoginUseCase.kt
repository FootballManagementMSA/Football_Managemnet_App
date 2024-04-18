package com.sejongunivclub.feature_login.domain.usecase

import com.sejongunivclub.core.ResultState.LoginResult
import com.sejongunivclub.core.datasource.UserRemoteDataSource
import com.sejongunivclub.core.model.LoginModel
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend operator fun invoke(loginModel: LoginModel): LoginResult {
        return userRemoteDataSource.login(loginModel)
    }
}