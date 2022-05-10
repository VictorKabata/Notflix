package com.vickikbt.notflix.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.cache.preferences.PreferenceManager
import kotlinx.coroutines.launch

class SettingsViewModel constructor(private val preferenceManager: PreferenceManager) :
    ViewModel() {

    fun savePreferenceSelection(key: String, selection: Int) = viewModelScope.launch {
        preferenceManager.setInt(key, selection)
    }

    val selectedTheme = preferenceManager.appTheme
    val selectedLanguage = preferenceManager.appLanguage
    val selectedImageQuality = preferenceManager.imageQuality


}