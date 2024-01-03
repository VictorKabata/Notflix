package com.vickbt.shared.data.datasources

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getIntFlow
import com.vickbt.shared.domain.repositories.SettingsRepository
import com.vickbt.shared.domain.utils.Constants.KEY_IMAGE_QUALITY
import com.vickbt.shared.domain.utils.Constants.KEY_THEME
import kotlinx.coroutines.flow.Flow

@ExperimentalSettingsApi
class SettingsRepositoryImpl(private val observableSettings: ObservableSettings) :
    SettingsRepository {

    override suspend fun savePreferenceSelection(key: String, selection: Int) =
        observableSettings.putInt(key = key, value = selection)

    override suspend fun getThemePreference(): Flow<Int> {
        return observableSettings.getIntFlow(key = KEY_THEME, defaultValue = 2)
    }

    override suspend fun getImageQualityPreference(): Flow<Int> {
        return observableSettings.getIntFlow(key = KEY_IMAGE_QUALITY, defaultValue = 1)
    }
}
