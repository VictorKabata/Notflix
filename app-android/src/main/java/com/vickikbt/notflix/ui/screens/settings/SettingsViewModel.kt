package com.vickikbt.notflix.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.repositories.SettingsRepository
import com.vickikbt.shared.utils.SettingsUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel constructor(private val settingsRepository: SettingsRepository) :
    ViewModel() {

    private val _settingsUiState = MutableStateFlow(SettingsUiState(isLoading = true))
    val settingsUiState = _settingsUiState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _settingsUiState.update { it.copy(isLoading = false, error = exception.message) }
    }

    fun savePreferenceSelection(key: String, selection: Int) =
        viewModelScope.launch(coroutineExceptionHandler) {
            settingsRepository.savePreferenceSelection(key = key, selection = selection)
        }

    fun getThemePreference() = viewModelScope.launch(coroutineExceptionHandler) {
        settingsRepository.getThemePreference().collect { theme ->
            theme?.let {
                _settingsUiState.update { it.copy(selectedTheme = theme, isLoading = false) }
            }
        }
    }

    fun getLanguagePreference() = viewModelScope.launch(coroutineExceptionHandler) {
        settingsRepository.getLanguagePreference().collect { language ->
            language?.let {
                _settingsUiState.update { it.copy(selectedLanguage = language, isLoading = false) }
            }
        }
    }

    fun getImageQualityPreference() = viewModelScope.launch(coroutineExceptionHandler) {
        settingsRepository.getImageQualityPreference().collect { imageQuality ->
            imageQuality?.let {
                _settingsUiState.update {
                    it.copy(
                        selectedImageQuality = imageQuality,
                        isLoading = false
                    )
                }
            }
        }
    }
}
