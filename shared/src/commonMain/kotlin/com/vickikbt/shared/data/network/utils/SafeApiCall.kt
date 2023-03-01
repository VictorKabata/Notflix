package com.vickikbt.shared.data.network.utils

import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.util.network.UnresolvedAddressException
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow

internal suspend fun <T : Any> safeApiCall(apiCall: suspend () -> T): Flow<Result<T>> = channelFlow {
    try {
        send(Result.success(apiCall.invoke()))
    } catch (e: RedirectResponseException) {
         val error = parseNetworkError(e.response.content)
        send(Result.failure(exception = error))
    } catch (e: ClientRequestException) {
         val error = parseNetworkError(e.response.content)
        send(Result.failure(exception = error))
    } catch (e: ServerResponseException) {
         val error = parseNetworkError(e.response.content)
        send(Result.failure(exception = error))
    } catch (e: UnresolvedAddressException) {
         val error = parseNetworkError(exception = e)
        send(Result.failure(exception = error))
    } catch (e: Exception) {
         val error = parseNetworkError(exception = e)
        send(Result.failure(exception = error))
    }
}

/**Generate [Exception] from network or system error when making network calls
 *
 * @throws [Exception]
 * */
internal suspend fun parseNetworkError(
    errorResponse: ByteReadChannel? = null,
    exception: Exception? = null
): Exception {
    throw exception?.cause ?: Exception()
}

