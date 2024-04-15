package com.example.feature_clubpage.domain.usecase

import com.example.core.datasource.ClubDataSource
import com.example.core.model.UserModel
import javax.inject.Inject


class GetJoinedMemberUseCase @Inject constructor(
    private val clubDataSource: ClubDataSource
){
    suspend operator fun invoke() : List<UserModel> {
        return clubDataSource.getJoinedMember()
    }
}