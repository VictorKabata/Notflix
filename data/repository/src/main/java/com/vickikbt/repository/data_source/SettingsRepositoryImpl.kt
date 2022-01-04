package com.vickikbt.repository.data_source

import com.vickikbt.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.flowOf

class SettingsRepositoryImpl constructor(private val themePreferences: ThemePreferences) :
    SettingsRepository {

    override suspend fun getSavedTheme() = flowOf(0) //= themePreferences.getSavedTheme()

    override suspend fun setTheme(selectedTheme: Int){}
    //themeDatastore.saveSelectedTheme(selectedTheme)

}