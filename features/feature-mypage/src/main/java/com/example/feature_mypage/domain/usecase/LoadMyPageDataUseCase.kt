package com.example.feature_mypage.domain.usecase

import com.example.core.datasource.MainHomeDataSource
import javax.inject.Inject

class LoadMyPageDataUseCase @Inject constructor(
    private val mainHomeDataSource: MainHomeDataSource
) {
    suspend operator fun invoke() = mainHomeDataSource.loadStudentData().data
}