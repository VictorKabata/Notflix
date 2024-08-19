package com.vickbt.shared.utils

sealed class ResultState<out T> {
    data class Success<out T : Any?>(val data: T) : ResultState<T>()

    data class Failure(val exception: Exception) : ResultState<Nothing>()

    object Loading : ResultState<Nothing>()
}

inline fun <T : Any?> ResultState<T>.isLoading(crossinline action: (isLoading: Boolean) -> Unit): ResultState<T> {
    if (this is ResultState.Loading) action(true) else action(false)
    return this
}

inline fun <T : Any?> ResultState<T>.onSuccess(crossinline action: (T) -> Unit): ResultState<T> {
    if (this is ResultState.Success) action(this.data)
    return this
}

inline fun <T : Any?> ResultState<T>.onFailure(crossinline action: (exception: Exception) -> Unit): ResultState<T> {
    if (this is ResultState.Failure) action(this.exception)
    return this
}
