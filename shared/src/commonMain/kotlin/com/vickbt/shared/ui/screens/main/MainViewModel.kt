package com.vickbt.shared.ui.screens.main

import androidx.lifecycle.ViewModel
import com.vickbt.shared.domain.repositories.SettingsRepository
import com.vickbt.shared.utils.MainUiState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {

    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState = _mainUiState.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.IO)

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
