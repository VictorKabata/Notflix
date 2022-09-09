package com.vickikbt.shared.presentation.presenters

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.vickikbt.shared.domain.repositories.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SharedSettingsPresenter constructor(private val settingsRepository: SettingsRepository) :
    KoinComponent {

    @NativeCoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Default)
    private val supervisorJob = MutableStateFlow<Job?>(null)

    private val _selectedTheme = MutableStateFlow<Int?>(0)
    val selectedTheme get() = _selectedTheme.asStateFlow()

    private val _selectedLanguage = MutableStateFlow<Int?>(0)
    val selectedLanguage get() = _selectedLanguage.asStateFlow()

    private val _selectedImageQuality = MutableStateFlow<Int?>(0)
    val selectedImageQuality get() = _selectedImageQuality.asStateFlow()

    init {
        getThemePreference()
        getLanguagePreference()
        getImageQualityPreference()
    }

    fun savePreferenceSelection(key: String, selection: Int) {
        val job = viewModelScope.launch {
            settingsRepository.savePreferenceSelection(key = key, selection = selection)
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    private fun getThemePreference() {
        val job = viewModelScope.launch {
            settingsRepository.getThemePreference().collectLatest {
                _selectedTheme.value = it
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    private fun getLanguagePreference() {
        val job = viewModelScope.launch {
            settingsRepository.getLanguagePreference().collectLatest {
                _selectedLanguage.value = it
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    private fun getImageQualityPreference() {
        val job = viewModelScope.launch {
            settingsRepository.getImageQualityPreference().collectLatest {
                _selectedImageQuality.value = it
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }
}
