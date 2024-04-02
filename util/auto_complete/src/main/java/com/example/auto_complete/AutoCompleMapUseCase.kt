package com.example.auto_complete

import com.example.core.datasource.ClubDataSource
import com.example.core.model.ClubInfo
import com.example.core.model.LocationInfo
import com.example.core.model.Map
import javax.inject.Inject

class AutoCompleteMapUseCase @Inject constructor(
    private val clubDataSource: ClubDataSource
){
    suspend operator fun invoke(code: String) : List<LocationInfo> {
        return clubDataSource.searchMap(code)
    }
}