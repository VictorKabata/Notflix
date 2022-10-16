package com.vickikbt.shared.data.datasources

import com.vickikbt.shared.data.cache.multiplatformsettings.PreferenceManager
import com.vickikbt.shared.data.cache.multiplatformsettings.PreferenceManager.Companion.IMAGE_QUALITY_KEY
import com.vickikbt.shared.data.cache.multiplatformsettings.PreferenceManager.Companion.LANGUAGE_KEY
import com.vickikbt.shared.data.cache.multiplatformsettings.PreferenceManager.Companion.THEME_KEY
import com.vickikbt.shared.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl constructor(private val preferenceManager: PreferenceManager) : SettingsRepository {

    override suspend fun savePreferenceSelection(key: String, selection: Int) =
        preferenceManager.setInt(key = key, value = selection)

    override suspend fun getThemePreference(): Flow<Int?> {
        return preferenceManager.getInt(key = THEME_KEY)
    }

    override suspend fun getLanguagePreference(): Flow<Int?> {
        return preferenceManager.getInt(key = LANGUAGE_KEY)
    }

    override suspend fun getImageQualityPreference(): Flow<Int?> {
        return preferenceManager.getInt(key = IMAGE_QUALITY_KEY)
    }
}
