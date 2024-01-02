package com.vickbt.shared.utils

sealed class NetworkResultState<out T> {
    data class Success<out T : Any?>(val data: T) : NetworkResultState<T>()
    data class Failure(val exception: Exception) : NetworkResultState<Nothing>()
    object Loading : NetworkResultState<Nothing>()
}

inline fun <T : Any?> NetworkResultState<T>.isLoading(crossinline action: (isLoading: Boolean) -> Unit): NetworkResultState<T> {
    if (this is NetworkResultState.Loading) action(true) else action(false)
    return this
}

inline fun <T : Any?> NetworkResultState<T>.onSuccess(crossinline action: (T) -> Unit): NetworkResultState<T> {
    if (this is NetworkResultState.Success) action(this.data)
    return this
}

inline fun <T : Any?> NetworkResultState<T>.onFailure(crossinline action: (exception: Exception) -> Unit): NetworkResultState<T> {
    if (this is NetworkResultState.Failure) action(this.exception)
    return this
}
