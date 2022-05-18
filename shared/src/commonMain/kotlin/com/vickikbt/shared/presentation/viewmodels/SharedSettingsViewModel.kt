package com.vickikbt.shared.presentation.viewmodels

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.vickikbt.shared.data.cache.multiplatform_settings.PreferenceManager
import com.vickikbt.shared.domain.utils.Constants.KEY_IMAGE_QUALITY
import com.vickikbt.shared.domain.utils.Constants.KEY_LANGUAGE
import com.vickikbt.shared.domain.utils.Constants.KEY_THEME
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SharedSettingsViewModel constructor(private val preferenceManager: PreferenceManager) :
    KoinComponent {

    @NativeCoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    private val _selectedTheme = MutableStateFlow<Int?>(0)
    val selectedTheme get() = _selectedTheme

    private val _selectedLanguage = MutableStateFlow<Int?>(0)
    val selectedLanguage get() = _selectedLanguage

    private val _selectedImageQuality = MutableStateFlow<Int?>(0)
    val selectedImageQuality get() = _selectedImageQuality

    init {
        getThemePreference()
        getLanguagePreference()
        getImageQualityPreference()
    }

    fun savePreferenceSelection(key: String, selection: Int) = viewModelScope.launch {
        Napier.e("Saving preference $key as $selection")
        preferenceManager.setInt(key, selection)
    }

    private fun getThemePreference() {
        viewModelScope.launch {
            preferenceManager.getInt(KEY_THEME).collectLatest {
                _selectedTheme.value = it
            }
        }
    }

    private fun getLanguagePreference() {
        viewModelScope.launch {
            preferenceManager.getInt(KEY_LANGUAGE).collectLatest {
                _selectedTheme.value = it
            }
        }
    }

    private fun getImageQualityPreference() {
        viewModelScope.launch {
            preferenceManager.getInt(KEY_IMAGE_QUALITY).collectLatest {
                _selectedTheme.value = it
            }
        }
    }
}
