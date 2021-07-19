package com.vickikbt.repository.repositories.settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun getSavedTheme(): Flow<Int?>

    suspend fun setTheme(selectedTheme: Int)

}