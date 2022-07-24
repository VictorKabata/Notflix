package com.vickikbt.shared.presentation.presenters

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.vickikbt.shared.domain.repositories.SettingsRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SharedMainPresenter constructor(private val settingsRepository: SettingsRepository) :
    KoinComponent {

    @NativeCoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Default)
    private val supervisorJob = MutableStateFlow<Job?>(null)

    private val _appTheme = MutableStateFlow<Int?>(null)
    val appTheme = _appTheme.asStateFlow()

    private val _appLanguage = MutableStateFlow<Int?>(null)
    val appLanguage = _appLanguage.asStateFlow()

    init {
        getAppTheme()
        getAppLanguage()
    }

    private fun getAppTheme() {
        val job = viewModelScope.launch {
            try {
                settingsRepository.getThemePreference().collectLatest { theme ->
                    _appTheme.value = theme
                }
            } catch (e: Exception) {
                Napier.e("ERROR saving theme: ${e.message}")
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    private fun getAppLanguage() {
        val job = viewModelScope.launch {
            try {
                settingsRepository.getLanguagePreference().collectLatest { language ->
                    _appLanguage.value = language
                }
            } catch (e: Exception) {
                Napier.e("ERROR saving theme: ${e.message}")
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }
}
