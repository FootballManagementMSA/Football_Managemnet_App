package com.sejongunivclub.presentation

import com.sejongunivclub.core.model.StudentUiModel

sealed class StudentDataState {
    data class Success(val data: StudentUiModel) : StudentDataState()
    data object Loading : StudentDataState()
}
