package com.vickikbt.shared.utils

sealed interface NetworkResultState<out T> {
    data class Success<out T : Any?>(val data: T) : NetworkResultState<T>
    data class Failure(val exception: Exception) : NetworkResultState<Nothing>
    object Loading : NetworkResultState<Nothing>
}
