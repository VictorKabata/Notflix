package com.vickikbt.repository.repositories.settings

import com.vickikbt.cache.datastore.ThemeDatastore
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val themeDatastore: ThemeDatastore) :
    SettingsRepository {

    override suspend fun getSavedTheme() = themeDatastore.getSavedTheme()

    override suspend fun setTheme(selectedTheme: Int) =
        themeDatastore.saveSelectedTheme(selectedTheme)

}