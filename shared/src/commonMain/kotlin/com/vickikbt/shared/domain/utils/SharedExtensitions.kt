package com.vickikbt.shared.domain.utils

import com.vickikbt.shared.presentation.presenters.SharedSettingsPresenter
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } }

fun KoinComponent.viewModelScope(operation: suspend () -> Unit) {
    val viewModelScope = CoroutineScope(Dispatchers.Default)
    val supervisorJob = MutableStateFlow<Job?>(null)

    val job = viewModelScope.launch { operation.invoke() }

    supervisorJob.value = job
    job.invokeOnCompletion {
        supervisorJob.value?.cancel()
        supervisorJob.value = null
    }
}

fun getAppLanguage(settingsPresenter: SharedSettingsPresenter): String {

    Napier.e("App language: ${settingsPresenter.selectedLanguage.value}")

    return when (settingsPresenter.selectedLanguage.value) {
        0 -> "en"
        1 -> "es"
        2 -> "fr"
        3 -> "de"
        else -> "en"
    }
}
