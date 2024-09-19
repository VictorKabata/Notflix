package com.vickbt.composeApp.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.composeApp.domain.repositories.SettingsRepository
import com.vickbt.composeApp.utils.MainUiState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {

    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState = _mainUiState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ -> }

    init {
        getAppTheme()
    }

    private fun getAppTheme() = viewModelScope.launch(coroutineExceptionHandler) {
        try {
            settingsRepository.getThemePreference().collectLatest { theme ->
                _mainUiState.update { it.copy(selectedTheme = theme) }
            }
        } catch (e: Exception) {
            Napier.e("ERROR getting theme: ${e.message}")
        }
    }
}
