package com.sejongunivclub.feature_joinclub.domain.usecase

import com.sejongunivclub.core.ResultState.ClubJoinRequestResult
import com.sejongunivclub.core.datasource.UserRemoteDataSource
import com.sejongunivclub.core.model.ClubJoinRequestModel
import javax.inject.Inject

class ClubJoinRequestUseCase @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend operator fun invoke(teamId: Long, clubJoinRequestModel: ClubJoinRequestModel):ClubJoinRequestResult {
        return userRemoteDataSource.clubJoinRequest(teamId, clubJoinRequestModel)
    }


}