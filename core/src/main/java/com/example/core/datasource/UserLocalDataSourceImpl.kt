package com.example.core.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.core.util.PreferenceKeys
import com.example.core.util.userDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
internal class UserLocalDataSourceImpl @Inject constructor(
    @ApplicationContext context: Context
) : UserLocalDataSource {
    private val dataStore = context.userDataStore
    override suspend fun saveAccessToken(accessToken: String) {
        dataStore.edit {
            it[PreferenceKeys.ACCESS_TOKEN] = accessToken
        }
    }

    override suspend fun saveAccount(account: String) {
        dataStore.edit {
            it[PreferenceKeys.ACCOUNT] = account
        }
    }

    override suspend fun savePassword(password: String) {
        dataStore.edit {
            it[PreferenceKeys.PASSWORD] = password
        }
    }

    override suspend fun saveRefreshToken(refreshToken: String) {
        dataStore.edit {
            it[PreferenceKeys.REFRESH_TOKEN] = refreshToken
        }
    }

    override suspend fun saveSelectedTeamUniqueNumber(uniqueNumber: String) {
        dataStore.edit {
            it[PreferenceKeys.UNIQUE_NUMBER] = uniqueNumber
        }
    }

    override suspend fun saveUserId(userId: Long) {
        dataStore.edit {
            it[PreferenceKeys.USER_ID] = userId
        }
    }

    override suspend fun saveSelectedTeamRole(role: String) {
        dataStore.edit {
            it[PreferenceKeys.TEAM_ROLE] = role
        }
    }

    override suspend fun saveSelectedTeamIntroduce(introduce: String) {
        dataStore.edit {
            it[PreferenceKeys.TEAM_INTRODUCE] = introduce
        }
    }

    override suspend fun saveSelectedTeamName(teamName: String) {
        dataStore.edit {
            it[PreferenceKeys.TEAM_NAME] = teamName
        }
    }

    override suspend fun saveSelectedTeamEmblem(teamEmblem: String) {
        dataStore.edit {
            it[PreferenceKeys.TEAM_EMBLEM] = teamEmblem
        }
    }

    override suspend fun saveSelectedTeamCreatedAt(createdAt: String) {
        dataStore.edit {
            it[PreferenceKeys.TEAM_CREATED_AT] = createdAt
        }
    }

    override suspend fun saveSelectedTeamSizeOfUsers(sizeOfUsers: Int) {
        dataStore.edit {
            it[PreferenceKeys.TEAM_SIZE_OF_USERS] = sizeOfUsers
        }
    }

    override suspend fun login() {
    }
    override suspend fun join(){
        // api 요청보내기
    }

    override suspend fun getAccessToken(): String {
        return dataStore.data.first()[PreferenceKeys.ACCESS_TOKEN] ?: ""

    }

    override suspend fun clearDataStore() {
        dataStore.edit {
            it.clear()
        }
    }

    override suspend fun getUserId(): Long {
        return dataStore.data.first()[PreferenceKeys.USER_ID] ?: 0
    }

    override suspend fun getSelectedTeamUniqueNumber(): String {
        return dataStore.data.first()[PreferenceKeys.UNIQUE_NUMBER] ?: ""
    }

    override suspend fun getSelectedTeamRole(): String {
        return dataStore.data.first()[PreferenceKeys.TEAM_ROLE] ?: ""
    }

    override suspend fun getSelectedTeamIntroduce(): String {
        return dataStore.data.first()[PreferenceKeys.TEAM_INTRODUCE] ?: ""
    }

    override suspend fun getSelectedTeamName(): String {
        return dataStore.data.first()[PreferenceKeys.TEAM_NAME] ?: ""
    }

    override suspend fun getSelectedTeamEmblem(): String {
        return dataStore.data.first()[PreferenceKeys.TEAM_EMBLEM] ?: ""
    }

    override suspend fun getSelectedTeamCreatedAt(): String {
        return dataStore.data.first()[PreferenceKeys.TEAM_CREATED_AT] ?: ""
    }

    override suspend fun getSelectedTeamSizeOfUsers(): Int {
        return dataStore.data.first()[PreferenceKeys.TEAM_SIZE_OF_USERS] ?: 0
    }
}