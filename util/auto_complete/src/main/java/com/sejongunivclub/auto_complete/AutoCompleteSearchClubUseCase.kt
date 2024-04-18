package com.sejongunivclub.auto_complete

import com.sejongunivclub.core.datasource.ClubDataSource
import com.sejongunivclub.core.model.ClubInfo
import javax.inject.Inject

class AutoCompleteSearchClubUseCase @Inject constructor(
    private val clubDataSource: ClubDataSource
){
    suspend operator fun invoke(code: String) : List<ClubInfo> {
        return clubDataSource.searchClub(code)
    }
}