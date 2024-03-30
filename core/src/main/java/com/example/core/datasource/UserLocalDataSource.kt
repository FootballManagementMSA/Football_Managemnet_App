package com.example.core.datasource

interface UserLocalDataSource {
    suspend fun saveAccessToken(accessToken: String)
    suspend fun saveAccount(account: String)
    suspend fun savePassword(password: String)
    suspend fun saveRefreshToken(refreshToken: String)
    suspend fun saveSelectedTeamUniqueNumber(uniqueNumber: String)
    suspend fun saveUserId(userId: Long)
    suspend fun login()
    suspend fun join()
    suspend fun getAccessToken(): String
    suspend fun clearDataStore()
    suspend fun getUserId(): Long
    suspend fun getSelectedTeamUniqueNumber(): String
}