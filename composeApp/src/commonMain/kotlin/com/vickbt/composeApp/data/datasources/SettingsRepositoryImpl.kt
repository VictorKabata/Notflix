package com.vickbt.composeApp.data.datasources

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.vickbt.composeApp.domain.repositories.SettingsRepository
import com.vickbt.composeApp.domain.utils.Constants.KEY_IMAGE_QUALITY
import com.vickbt.composeApp.domain.utils.Constants.KEY_THEME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {

    override suspend fun savePreferenceSelection(key: String, selection: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = selection
        }
    }

    override suspend fun getThemePreference(): Result<Flow<Int>> {
        return runCatching {
            dataStore.data.map { preferences ->
                preferences[intPreferencesKey(KEY_THEME)] ?: 2
            }
        }
    }

    override suspend fun getImageQualityPreference(): Result<Flow<Int>> {
        return runCatching {
            dataStore.data.map { preferences ->
                preferences[intPreferencesKey(KEY_IMAGE_QUALITY)] ?: 1
            }
        }
    }
}
