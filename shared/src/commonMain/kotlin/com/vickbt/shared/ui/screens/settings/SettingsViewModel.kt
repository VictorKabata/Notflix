package com.vickbt.shared.ui.screens.settings

import com.vickbt.shared.domain.repositories.SettingsRepository
import com.vickbt.shared.utils.SettingsUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SettingsViewModel(private val settingsRepository: SettingsRepository) :
    KoinComponent {

    private val _settingsUiState = MutableStateFlow(SettingsUiState(isLoading = true))
    val settingsUiState = _settingsUiState.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.IO)

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
        settingsRepository.getThemePreference().collect { theme ->
            _settingsUiState.update { it.copy(selectedTheme = theme, isLoading = false) }
        }
    }

    fun getImageQualityPreference() = viewModelScope.launch(coroutineExceptionHandler) {
        settingsRepository.getImageQualityPreference().collect { imageQuality ->
            _settingsUiState.update {
                it.copy(
                    selectedImageQuality = imageQuality,
                    isLoading = false
                )
            }
        }
    }
}
