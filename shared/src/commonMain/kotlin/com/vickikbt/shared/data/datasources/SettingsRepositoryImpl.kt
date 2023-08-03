package com.vickikbt.shared.data.datasources

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getIntOrNullFlow
import com.vickikbt.shared.domain.repositories.SettingsRepository
import com.vickikbt.shared.domain.utils.Constants.KEY_IMAGE_QUALITY
import com.vickikbt.shared.domain.utils.Constants.KEY_LANGUAGE
import com.vickikbt.shared.domain.utils.Constants.KEY_THEME
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl constructor(private val observableSettings: ObservableSettings) :
    SettingsRepository {

    override suspend fun savePreferenceSelection(key: String, selection: Int) =
        observableSettings.putInt(key = key, value = selection)

    override suspend fun getThemePreference(): Flow<Int?> {
        return observableSettings.getIntOrNullFlow(key = KEY_THEME)
    }

    override suspend fun getImageQualityPreference(): Flow<Int?> {
        return observableSettings.getIntOrNullFlow(key = KEY_IMAGE_QUALITY)
    }
}
