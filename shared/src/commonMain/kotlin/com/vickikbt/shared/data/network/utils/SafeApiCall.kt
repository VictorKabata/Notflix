package com.vickikbt.shared.data.network.utils

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException

internal suspend fun <T : Any> safeApiCall(apiCall: suspend () -> T): Result<T> = try {
    Result.success(apiCall.invoke())
} catch (e: RedirectResponseException) {
    val error = parseNetworkError(e.response.body())
    Result.failure(exception = error)
} catch (e: ClientRequestException) {
    val error = parseNetworkError(e.response.body())
    Result.failure(exception = error)
} catch (e: ServerResponseException) {
    val error = parseNetworkError(e.response.body())
    Result.failure(exception = error)
} catch (e: UnresolvedAddressException) {
    val error = parseNetworkError(exception = e)
    Result.failure(exception = error)
} catch (e: Exception) {
    val error = parseNetworkError(exception = e)
    Result.failure(exception = error)
}

/**Generate [Exception] from network or system error when making network calls
 *
 * @throws [Exception]
 * */
internal suspend fun parseNetworkError(
    errorResponse: HttpResponse? = null,
    exception: Exception? = null
): Exception {
    throw errorResponse?.body<Exception>() ?: Exception()
}
