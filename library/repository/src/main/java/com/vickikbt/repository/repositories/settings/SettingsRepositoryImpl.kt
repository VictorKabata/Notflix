package com.vickikbt.repository.repositories.settings

import com.vickikbt.cache.preferences.ThemePreferences
import kotlinx.coroutines.flow.flowOf

class SettingsRepositoryImpl constructor(private val themePreferences: ThemePreferences) :
    SettingsRepository {

    override suspend fun getSavedTheme() = flowOf(0) //= themePreferences.getSavedTheme()

    override suspend fun setTheme(selectedTheme: Int){}
    //themeDatastore.saveSelectedTheme(selectedTheme)

}