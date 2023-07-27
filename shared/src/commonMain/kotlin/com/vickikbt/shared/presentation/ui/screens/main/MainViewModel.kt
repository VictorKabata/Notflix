package com.vickikbt.shared.presentation.ui.screens.main

import com.vickikbt.shared.domain.repositories.SettingsRepository
import com.vickikbt.shared.utils.MainUiState
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
import org.koin.core.component.KoinComponent

class MainViewModel(private val settingsRepository: SettingsRepository) : KoinComponent {

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
                _mainUiState.update { it.copy(appTheme = theme) }
            }
        } catch (e: Exception) {
            Napier.e("ERROR getting theme: ${e.message}")
        }
    }

}
