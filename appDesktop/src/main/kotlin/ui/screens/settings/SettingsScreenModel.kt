package ui.screens.settings

import cafe.adriel.voyager.core.model.ScreenModel
import com.vickikbt.shared.domain.repositories.SettingsRepository
import com.vickikbt.shared.utils.SettingsUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsScreenModel constructor(private val settingsRepository: SettingsRepository) :
    ScreenModel {

    private val _settingsUiState = MutableStateFlow(SettingsUiState(isLoading = true))
    val settingsUiState = _settingsUiState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _settingsUiState.update { it.copy(isLoading = false, error = exception.message) }
    }

    fun savePreferenceSelection(key: String, selection: Int) =
        CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
            settingsRepository.savePreferenceSelection(key = key, selection = selection)
        }

    fun getThemePreference() = CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
        settingsRepository.getThemePreference().collect { theme ->
            theme?.let {
                _settingsUiState.update { it.copy(selectedTheme = theme, isLoading = false) }
            }
        }
    }

    fun getImageQualityPreference() =
        CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
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
