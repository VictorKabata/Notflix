package com.vickbt.shared.domain.repositories

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun savePreferenceSelection(key: String, selection: Int)

    suspend fun getThemePreference(): Flow<Int?>

    suspend fun getImageQualityPreference(): Flow<Int?>
}
