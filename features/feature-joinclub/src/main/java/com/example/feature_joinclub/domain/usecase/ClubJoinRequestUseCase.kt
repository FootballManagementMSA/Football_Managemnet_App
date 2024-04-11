package com.example.feature_joinclub.domain.usecase

import com.example.core.ResultState.ClubJoinRequestResult
import com.example.core.datasource.UserRemoteDataSource
import com.example.core.model.ClubJoinRequestModel
import javax.inject.Inject

class ClubJoinRequestUseCase @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend operator fun invoke(teamId: Long, clubJoinRequestModel: ClubJoinRequestModel):ClubJoinRequestResult {
        return userRemoteDataSource.clubJoinRequest(teamId, clubJoinRequestModel)
    }


}