package com.sejongunivclub.feature_makeclub.domain

import com.sejongunivclub.core.ResultState.MakeClubResult
import com.sejongunivclub.core.datasource.ClubDataSource
import com.sejongunivclub.core.model.MakeClubModel
import javax.inject.Inject

class SendClubInfoDataUseCase @Inject constructor(
    private val clubDataSource: ClubDataSource
) {
    suspend operator fun invoke(makeClubModel: MakeClubModel): MakeClubResult {
        return clubDataSource.sendClubInfo(makeClubModel)
    }
}