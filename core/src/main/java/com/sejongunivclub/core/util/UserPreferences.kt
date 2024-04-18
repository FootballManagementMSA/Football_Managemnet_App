package com.sejongunivclub.core.util

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.userDataStore by preferencesDataStore(
    name = "UserPreferences"
)

object PreferenceKeys {
    val FCM_TOKEN= stringPreferencesKey("FCM_TOKEN")
    val ACCESS_TOKEN = stringPreferencesKey("ACEESS_TOKEN")
    val REFRESH_TOKEN = stringPreferencesKey("REFRESH_TOKEN")
    val ACCOUNT = stringPreferencesKey("ACCOUNT")
    val PASSWORD = stringPreferencesKey("PASSWORD")
    val UNIQUE_NUMBER = stringPreferencesKey("UNIQUE_NUMBER")
    val USER_ID = longPreferencesKey("USER_ID")
    val TEAM_ROLE = stringPreferencesKey("TEAM_ROLE")
    val TEAM_INTRODUCE = stringPreferencesKey("TEAM_INTRODUCE")
    val TEAM_NAME = stringPreferencesKey("TEAM_NAME")
    val TEAM_EMBLEM = stringPreferencesKey("TEAM_EMBLEM")
    val TEAM_CREATED_AT = stringPreferencesKey("TEAM_CREATED_AT")
    val TEAM_SIZE_OF_USERS = intPreferencesKey("TEAM_SIZE_OF_USERS")
    val TEAM_ID = longPreferencesKey("TEAM_ID")
}