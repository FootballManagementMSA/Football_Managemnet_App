package com.sejongunivclub.feature_joinclub.domain.usecase

import com.sejongunivclub.core.datasource.UserLocalDataSource
import com.sejongunivclub.core.util.JoinedClubDataUtil
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