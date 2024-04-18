package com.sejongunivclub.auto_complete

import com.sejongunivclub.core.datasource.ClubDataSource
import com.sejongunivclub.core.model.LocationInfo
import javax.inject.Inject

class AutoCompleteMapUseCase @Inject constructor(
    private val clubDataSource: ClubDataSource
){
    suspend operator fun invoke(code: String) : List<LocationInfo> {
        return clubDataSource.searchMap(code)
    }
}