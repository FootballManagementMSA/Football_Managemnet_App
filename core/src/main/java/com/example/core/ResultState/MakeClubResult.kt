package com.example.core.ResultState

sealed class MakeClubResult {
    data class Success(val uniqueNumber: String) : MakeClubResult()
    data class Error(val errorMessage: String) : MakeClubResult()
    data object Initial : MakeClubResult()
    data object Loading : MakeClubResult()
}

sealed class MakeClubScheduleResult {
    data class Success(val uniqueNumber: Int) : MakeClubScheduleResult()
    data class Error(val errorMessage: String) : MakeClubScheduleResult()
}
