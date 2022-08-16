package com.vickikbt.shared.data.network.utils

import io.ktor.client.features.*

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
    return try {
        NetworkResult.Success(data = apiCall.invoke())
    } catch (e: RedirectResponseException) { // 3xx errors
        NetworkResult.Error(
            errorCode = e.response.status.value,
            errorMessage = e.message
        )
    } catch (e: ClientRequestException) { // 4xx errors
        NetworkResult.Error(
            errorCode = e.response.status.value,
            errorMessage = e.message
        )
    } catch (e: ServerResponseException) { // 5xx errors
        NetworkResult.Error(
            errorCode = e.response.status.value,
            errorMessage = e.message
        )
    } catch (e: Exception) {
        NetworkResult.Error(
            errorCode = 0,
            errorMessage = e.message ?: "An unknown error occurred"
        )
    }
}
