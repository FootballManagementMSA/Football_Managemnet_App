package com.sejongunivclub.domain.usecase

import com.sejongunivclub.core.datasource.MainHomeDataSource
import javax.inject.Inject

class LoadStudentDataUseCase @Inject constructor(
    private val mainHomeDataSource: MainHomeDataSource
) {
    suspend operator fun invoke() = mainHomeDataSource.loadStudentData().data
}