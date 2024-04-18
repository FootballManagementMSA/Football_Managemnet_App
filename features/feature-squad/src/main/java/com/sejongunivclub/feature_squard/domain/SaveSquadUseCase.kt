package com.sejongunivclub.feature_squard.domain

import com.sejongunivclub.core.datasource.SquadDataSource
import com.sejongunivclub.core.model.UserData
import javax.inject.Inject

class SaveSquadUseCase @Inject constructor(
    private val squadDataSource: SquadDataSource
) {
    suspend operator fun invoke(
        teamId: Long,
        scheduleId: Long,
        userData: UserData
    ) {
        squadDataSource.saveSquad(teamId, scheduleId, userData)
    }
}