package com.vickikbt.shared.data.network.utils

import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.network.models.ErrorResponseDto
import com.vickikbt.shared.domain.models.ErrorResponse
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> T): Flow<Result<T>> =
    channelFlow {
        try {
            send(Result.success(apiCall.invoke()))
        } catch (e: RedirectResponseException) {
            val error = parseNetworkError(e.response.body())
            send(Result.failure(exception = error))
        } catch (e: ClientRequestException) {
            val error = parseNetworkError(e.response.body())
            send(Result.failure(exception = error))
        } catch (e: ServerResponseException) {
            val error = parseNetworkError(e.response.body())
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
    errorResponse: HttpResponse? = null,
    exception: Exception? = null
): Exception {
    throw return try {
        errorResponse?.body<ErrorResponseDto>()?.toDomain() ?: ErrorResponse(
            success = false,
            statusCode = 0,
            statusMessage = exception?.message ?: "Error"
        )
    } catch (e: Exception) {
        e
    }
}
