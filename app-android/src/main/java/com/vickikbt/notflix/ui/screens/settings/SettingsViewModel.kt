package com.vickikbt.notflix.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsViewModel constructor(private val settingsRepository: SettingsRepository) :
    ViewModel() {

    private val _selectedTheme = MutableStateFlow<Int?>(null)
    val selectedTheme get() = _selectedTheme.asStateFlow()

    private val _selectedLanguage = MutableStateFlow<Int?>(null)
    val selectedLanguage get() = _selectedLanguage.asStateFlow()

    private val _selectedImageQuality = MutableStateFlow<Int?>(null)
    val selectedImageQuality get() = _selectedImageQuality.asStateFlow()

    init {
        getThemePreference()
        getLanguagePreference()
        getImageQualityPreference()
    }

    fun savePreferenceSelection(key: String, selection: Int) = viewModelScope.launch {
        settingsRepository.savePreferenceSelection(key = key, selection = selection)
    }

    private fun getThemePreference() = viewModelScope.launch {
        settingsRepository.getThemePreference().collectLatest {
            _selectedTheme.value = it ?: 2
        }
    }

    private fun getLanguagePreference() = viewModelScope.launch {
        settingsRepository.getLanguagePreference().collectLatest {
            _selectedLanguage.value = it ?: 0
        }
    }

    private fun getImageQualityPreference() = viewModelScope.launch {
        settingsRepository.getImageQualityPreference().collectLatest {
            _selectedImageQuality.value = it ?: 2
        }
    }
}
