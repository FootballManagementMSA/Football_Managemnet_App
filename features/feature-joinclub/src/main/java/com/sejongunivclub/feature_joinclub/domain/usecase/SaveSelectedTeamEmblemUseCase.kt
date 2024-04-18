package com.sejongunivclub.feature_joinclub.domain.usecase

import com.sejongunivclub.core.datasource.UserLocalDataSource
import javax.inject.Inject

class SaveSelectedTeamEmblemUseCase @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) {
    suspend operator fun invoke(emblem: String) {
        userLocalDataSource.saveSelectedTeamEmblem(emblem)
    }
}