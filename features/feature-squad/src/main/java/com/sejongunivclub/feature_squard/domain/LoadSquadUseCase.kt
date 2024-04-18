package com.sejongunivclub.feature_squard.domain

import com.sejongunivclub.core.datasource.SquadDataSource
import javax.inject.Inject

class LoadSquadUseCase @Inject constructor(
    private val squadDataSource: SquadDataSource
) {
    suspend operator fun invoke(
        teamId: Long,
        scheduleId: Long,
    ) = squadDataSource.loadSquad(teamId, scheduleId)

}