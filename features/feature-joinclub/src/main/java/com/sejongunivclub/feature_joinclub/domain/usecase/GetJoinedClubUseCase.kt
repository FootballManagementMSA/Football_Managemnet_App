package com.sejongunivclub.feature_joinclub.domain.usecase

import com.sejongunivclub.core.datasource.ClubDataSource
import com.sejongunivclub.core.model.UserTeamInfoModel
import javax.inject.Inject

class GetJoinedClubUseCase @Inject constructor(
    private val clubDataSource: ClubDataSource
){
    suspend operator fun invoke() : List<UserTeamInfoModel> {
        return clubDataSource.getJoinedClub()
    }
}