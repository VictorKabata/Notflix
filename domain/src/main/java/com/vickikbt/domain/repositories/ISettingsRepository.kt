package com.vickikbt.domain.repositories

import kotlinx.coroutines.flow.Flow

interface ISettingsRepository {

    suspend fun getSavedTheme(): Flow<Int?>

    suspend fun setTheme(selectedTheme: Int)

}