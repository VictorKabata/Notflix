package com.vickikbt.notflix.ui.screens.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.cache.preferences.PreferenceManager
import kotlinx.coroutines.launch

class SettingsViewModel constructor(private val preferenceManager: PreferenceManager) :
    ViewModel() {

    private val _selectedLanguage = MutableLiveData<String>()
    val selectedLanguage: LiveData<String> get() = _selectedLanguage

    private val _selectedImageQuality = MutableLiveData<String>()
    val selectedImageQuality: LiveData<String> get() = _selectedImageQuality


    fun savePreferenceSelection(key: String, selection: String) = viewModelScope.launch {
        preferenceManager.setString(key, selection)
    }

    val selectedTheme = preferenceManager.appTheme


}