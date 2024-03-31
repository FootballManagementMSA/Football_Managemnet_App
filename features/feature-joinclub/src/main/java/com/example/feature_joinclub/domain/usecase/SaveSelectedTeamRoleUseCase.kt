package com.example.feature_joinclub.domain.usecase

import com.example.core.datasource.UserLocalDataSource
import javax.inject.Inject

class SaveSelectedTeamRoleUseCase @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) {
    suspend operator fun invoke(role: String) {
        userLocalDataSource.saveSelectedTeamRole(role)
    }
}