package com.example.feature_joinclub.domain.usecase

import com.example.core.datasource.ClubDataSource
import com.example.core.model.UserTeamInfoModel
import javax.inject.Inject

class GetJoinedClubUseCase @Inject constructor(
    private val clubDataSource: ClubDataSource
){
    suspend operator fun invoke() : List<UserTeamInfoModel> {
        return clubDataSource.getJoinedClub()
    }
}