package com.vickbt.composeApp.data.network.utils

import com.vickbt.composeApp.data.mappers.toDomain
import com.vickbt.composeApp.data.network.models.ErrorResponseDto
import com.vickbt.composeApp.domain.models.ErrorResponse
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

suspend fun <T : Any?> safeApiCall(apiCall: suspend () -> T): Result<Flow<T>> {
    return runCatching {
        flowOf(apiCall.invoke())
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
    throw errorResponse?.body<ErrorResponseDto>()?.toDomain() ?: ErrorResponse(
        success = false,
        statusCode = 0,
        statusMessage = exception?.message ?: "Error"
    )
}
