package com.vickbt.composeApp.domain.repositories

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun savePreferenceSelection(key: String, selection: Int)

    suspend fun getThemePreference(): Result<Flow<Int>>

    suspend fun getImageQualityPreference(): Result<Flow<Int>>
}
