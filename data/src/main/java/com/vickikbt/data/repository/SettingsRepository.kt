package com.vickikbt.data.repository

import com.vickikbt.data.sources.SettingsDataSource
import com.vickikbt.domain.repositories.ISettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepository constructor(private val settingsDataSource: SettingsDataSource):ISettingsRepository {

    override suspend fun getSavedTheme()=settingsDataSource.getSavedTheme()

    override suspend fun setTheme(selectedTheme: Int) {
        TODO("Not yet implemented")
    }
}