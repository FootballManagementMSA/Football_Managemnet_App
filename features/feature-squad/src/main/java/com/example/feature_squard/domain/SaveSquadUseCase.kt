package com.example.feature_squard.domain

import com.example.core.datasource.SquadDataSource
import javax.inject.Inject

class SaveSquadUseCase @Inject constructor(
    private val squadDataSource: SquadDataSource
) {
    suspend operator fun invoke() {}
}