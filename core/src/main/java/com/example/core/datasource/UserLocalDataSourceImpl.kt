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

    override suspend fun saveUniqueNumber(uniqueNumber: String) {
        dataStore.edit {
            it[PreferenceKeys.UNIQUE_NUMBER] = uniqueNumber
        }
    }

    override suspend fun saveUserId(userId: Long) {
        dataStore.edit {
            it[PreferenceKeys.USER_ID] = userId
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
}