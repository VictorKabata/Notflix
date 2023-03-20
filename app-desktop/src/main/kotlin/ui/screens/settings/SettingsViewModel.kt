package ui.screens.settings

import com.vickikbt.shared.domain.repositories.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SettingsViewModel constructor(private val settingsRepository: SettingsRepository) :
    KoinComponent {

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

    fun savePreferenceSelection(key: String, selection: Int) =
        CoroutineScope(Dispatchers.IO).launch {
            settingsRepository.savePreferenceSelection(key = key, selection = selection)
        }

    private fun getThemePreference() = CoroutineScope(Dispatchers.IO).launch {
        settingsRepository.getThemePreference().collect {
            _selectedTheme.value = it ?: 2
        }
    }

    private fun getLanguagePreference() = CoroutineScope(Dispatchers.IO).launch {
        settingsRepository.getLanguagePreference().collect {
            _selectedLanguage.value = it ?: 0
        }
    }

    private fun getImageQualityPreference() = CoroutineScope(Dispatchers.IO).launch {
        settingsRepository.getImageQualityPreference().collect {
            _selectedImageQuality.value = it ?: 2
        }
    }
}
