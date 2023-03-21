package com.vickikbt.notflix.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.repositories.SettingsRepository
import com.vickikbt.shared.utils.SettingsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel constructor(private val settingsRepository: SettingsRepository) :
    ViewModel() {

    private val _settingsUiState = MutableStateFlow(SettingsUiState(isLoading = true))
    val settingsUiState = _settingsUiState.asStateFlow()

    init {
        getThemePreference()
        getLanguagePreference()
        getImageQualityPreference()
    }

    fun savePreferenceSelection(key: String, selection: Int) = viewModelScope.launch {
        settingsRepository.savePreferenceSelection(key = key, selection = selection)
    }

    private fun getThemePreference() = viewModelScope.launch {
        settingsRepository.getThemePreference().collect { theme ->
            theme?.let {
                _settingsUiState.update { it.copy(selectedTheme = theme) }
            }
        }
    }

    private fun getLanguagePreference() = viewModelScope.launch {
        settingsRepository.getLanguagePreference().collect { language ->
            language?.let {
                _settingsUiState.update { it.copy(selectedLanguage = language) }
            }
        }
    }

    private fun getImageQualityPreference() = viewModelScope.launch {
        settingsRepository.getImageQualityPreference().collect { imageQuality ->
            imageQuality?.let {
                _settingsUiState.update { it.copy(selectedImageQuality = imageQuality) }
            }
        }
    }
}
