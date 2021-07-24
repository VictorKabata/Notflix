package com.vickikbt.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun getSavedTheme(): Flow<Int?>

    suspend fun setTheme(selectedTheme: Int)

}