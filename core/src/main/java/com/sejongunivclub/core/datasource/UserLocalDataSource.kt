package com.sejongunivclub.core.datasource

interface UserLocalDataSource {

    suspend fun saveFcmToken(fcmToken: String)
    suspend fun saveAccessToken(accessToken: String)
    suspend fun saveAccount(account: String)
    suspend fun savePassword(password: String)
    suspend fun saveRefreshToken(refreshToken: String)
    suspend fun saveSelectedTeamUniqueNumber(uniqueNumber: String)
    suspend fun saveUserId(userId: Long)
    suspend fun saveSelectedTeamRole(role: String)
    suspend fun saveSelectedTeamIntroduce(introduce: String)
    suspend fun saveSelectedTeamName(teamName: String)
    suspend fun saveSelectedTeamEmblem(teamEmblem: String)
    suspend fun saveSelectedTeamCreatedAt(createdAt: String)
    suspend fun saveSelectedTeamSizeOfUsers(sizeOfUsers: Int)
    suspend fun saveSelectedTeamId(teamId: Long)
    suspend fun login()
    suspend fun join()
    suspend fun getAccessToken(): String
    suspend fun clearDataStore()
    suspend fun getUserId(): Long
    suspend fun getSelectedTeamUniqueNumber(): String
    suspend fun getSelectedTeamRole(): String
    suspend fun getSelectedTeamIntroduce(): String
    suspend fun getSelectedTeamName(): String
    suspend fun getSelectedTeamEmblem(): String
    suspend fun getSelectedTeamCreatedAt(): String
    suspend fun getSelectedTeamSizeOfUsers(): Int
    suspend fun getSelectedTeamId(): Long
}