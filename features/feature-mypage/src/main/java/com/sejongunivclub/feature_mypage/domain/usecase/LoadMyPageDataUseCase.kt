package com.sejongunivclub.feature_mypage.domain.usecase

import com.sejongunivclub.core.datasource.MainHomeDataSource
import javax.inject.Inject

class LoadMyPageDataUseCase @Inject constructor(
    private val mainHomeDataSource: MainHomeDataSource
) {
    suspend operator fun invoke() = mainHomeDataSource.loadStudentData().data
}