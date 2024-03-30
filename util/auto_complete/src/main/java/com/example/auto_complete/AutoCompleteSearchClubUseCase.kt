package com.example.auto_complete

import com.example.core.datasource.ClubDataSource
import com.example.core.model.ClubInfo
import javax.inject.Inject

class AutoCompleteSearchClubUseCase @Inject constructor(
    private val clubDataSource: ClubDataSource
){
    suspend operator fun invoke(code: String) : List<ClubInfo> {
        return clubDataSource.searchClub(code)
    }
}