package com.vickikbt.shared.utils

sealed class UiState<out T : Any?> {
    data class Success<out T : Any>(val data: T) : UiState<T>()
    data class Error(val exception: Exception) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
}

inline fun <T : Any> UiState<T>.onSuccess(crossinline action: (T) -> Unit): UiState<T> {
    if (this is UiState.Success) action(this.data)
    return this
}

inline fun <T : Any> UiState<T>.onFailure(crossinline action: (exception: Exception) -> Unit): UiState<T> {
    if (this is UiState.Error) action(this.exception)
    return this
}

inline fun <T : Any> UiState<T>.onLoading(crossinline action: (isLoading: Boolean) -> Unit): UiState<T> {
    if (this is UiState.Loading) action(true)
    return this
}
