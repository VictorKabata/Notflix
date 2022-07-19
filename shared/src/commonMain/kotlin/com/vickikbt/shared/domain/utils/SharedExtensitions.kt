package com.vickikbt.shared.domain.utils

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
