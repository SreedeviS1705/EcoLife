package com.welkinwits.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("settings")

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext appContext: Context) {

    private val STUD_ID = stringPreferencesKey("studId")
    private val TOKEN = stringPreferencesKey("token")
    private val ONBOAARDINGSTATUS = booleanPreferencesKey("onBoardingStatus")

    private val settingsDataStore = appContext.dataStore

    suspend fun setStudId(studId: String) {
        settingsDataStore.edit { settings ->
            settings[STUD_ID] = studId
        }
    }

    val studId: Flow<String?> = settingsDataStore.data.map { preferences ->
        preferences[STUD_ID]
    }


    suspend fun setToken(token: String) {
        settingsDataStore.edit { settings ->
            settings[TOKEN] = token
        }
    }

    suspend fun setOnBoardingStatus(onBoardingStatus: Boolean) {
        settingsDataStore.edit { settings ->
            settings[ONBOAARDINGSTATUS] = onBoardingStatus
        }
    }

    val token: Flow<String?> = settingsDataStore.data.map { preferences ->
        preferences[TOKEN]
    }

    val onBoardingStatus: Flow<Boolean?> = settingsDataStore.data.map { preferences ->
        preferences[ONBOAARDINGSTATUS]
    }

    suspend fun clear() {
        settingsDataStore.edit {
            it.clear()
        }
    }

}