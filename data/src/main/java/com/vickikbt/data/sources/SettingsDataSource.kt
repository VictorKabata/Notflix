package com.vickikbt.data.sources

import com.vickikbt.data.datastore.ThemeDatastore
import kotlinx.coroutines.flow.flowOf

class SettingsDataSource constructor(private val themeDatastore: ThemeDatastore) {

    suspend fun getSavedTheme() = themeDatastore.getSavedTheme()

    suspend fun setTheme(selectedTheme:Int)=themeDatastore.saveSelectedTheme(selectedTheme)

}