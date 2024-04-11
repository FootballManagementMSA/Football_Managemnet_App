package com.example.feature_joinclub.domain.usecase

import com.example.core.datasource.UserLocalDataSource
import com.example.core.util.JoinedClubDataUtil
import javax.inject.Inject

class SaveSelectedTeamIdUseCase @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) {
    suspend operator fun invoke(uniqueNum: String) {
        val clubData = JoinedClubDataUtil.getClubDataList()
        val selectedTeamId = clubData.firstOrNull {
            it.uniqueNum == uniqueNum
        }
        userLocalDataSource.saveSelectedTeamId(selectedTeamId!!.id)
    }
}