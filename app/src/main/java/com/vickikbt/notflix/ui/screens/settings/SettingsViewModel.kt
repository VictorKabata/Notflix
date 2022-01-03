package com.vickikbt.notflix.ui.screens.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.cache.datastore.DatastoreManager
import com.vickikbt.domain.utils.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsViewModel constructor(private val datastoreManager: DatastoreManager) : ViewModel() {

    private val _selectedTheme = MutableLiveData<String>()
    val selectedTheme: LiveData<String> get() = _selectedTheme

    private val _selectedLanguage = MutableLiveData<String>()
    val selectedLanguage: LiveData<String> get() = _selectedLanguage

    private val _selectedImageQuality = MutableLiveData<String>()
    val selectedImageQuality: LiveData<String> get() = _selectedImageQuality

    init {
        getThemeSelection()
        getLanguageSelection()
        getImageQualitySelection()
    }

    fun savePreferenceSelection(key: String, selection: String) = viewModelScope.launch {
        datastoreManager.saveString(key, selection)
    }

    private fun getThemeSelection() = viewModelScope.launch {
        val themeSelection = datastoreManager.getString(Constants.KEY_THEME, "System default")
        themeSelection.collectLatest { theme ->
            _selectedTheme.value = theme
        }
    }

    private fun getLanguageSelection() = viewModelScope.launch {
        val languageSelection = datastoreManager.getString(Constants.KEY_LANGUAGE, "No Russian")
        languageSelection.collectLatest { language ->
            _selectedLanguage.value = language
        }
    }

    private fun getImageQualitySelection() = viewModelScope.launch {
        val imageQualitySelection = datastoreManager.getString(Constants.KEY_IMAGE_QUALITY, "Low Quality")
        imageQualitySelection.collectLatest { imageQuality ->
            _selectedImageQuality.value = imageQuality
        }
    }

}