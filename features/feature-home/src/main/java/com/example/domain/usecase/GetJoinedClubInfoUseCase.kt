package com.example.domain.usecase

import com.example.core.datasource.MainHomeDataSource
import javax.inject.Inject

class GetJoinedClubInfoUseCase @Inject constructor(
    private val mainHomeDataSource: MainHomeDataSource
) {
    suspend operator fun invoke() = mainHomeDataSource.getJoinedClubInfo()
}