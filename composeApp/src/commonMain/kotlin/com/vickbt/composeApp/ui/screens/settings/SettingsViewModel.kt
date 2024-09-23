package com.vickbt.composeApp.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.composeApp.domain.repositories.SettingsRepository
import com.vickbt.composeApp.utils.SettingsUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepository: SettingsRepository) :
    ViewModel() {

    private val _settingsUiState = MutableStateFlow(SettingsUiState(isLoading = true))
    val settingsUiState = _settingsUiState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _settingsUiState.update { it.copy(isLoading = false, error = exception.message) }
    }

    init {
        getThemePreference()
        getImageQualityPreference()
    }

    fun savePreferenceSelection(key: String, selection: Int) =
        viewModelScope.launch(coroutineExceptionHandler) {
            settingsRepository.savePreferenceSelection(key = key, selection = selection)
        }

    fun getThemePreference() = viewModelScope.launch(coroutineExceptionHandler) {
        settingsRepository.getThemePreference().onSuccess { data ->
            data.collectLatest { theme ->
                _settingsUiState.update { it.copy(selectedTheme = theme, isLoading = false) }
            }
        }
    }

    fun getImageQualityPreference() = viewModelScope.launch(coroutineExceptionHandler) {
        settingsRepository.getImageQualityPreference().onSuccess { data ->
            data.collectLatest { imageQuality ->
                _settingsUiState.update {
                    it.copy(selectedImageQuality = imageQuality, isLoading = false)
                }
            }
        }
    }
}
