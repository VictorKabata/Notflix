package com.vickikbt.shared.data.network

import com.vickikbt.shared.SampleData.Companion.MOCK_ERROR_RESPONSE
import com.vickikbt.shared.SampleData.Companion.MOCK_MOVIE_RESPONSE
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*

class MockServer {

    companion object {
        private var networkSuccess: Boolean = true

        val mockHttpClient = HttpClient(MockEngine) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }

            engine {
                addHandler { request ->
                    when (request.url.encodedPath) {
                        "/movie/now_playing" -> {
                            respond(
                                content = if (networkSuccess) MOCK_MOVIE_RESPONSE else MOCK_ERROR_RESPONSE,
                                status = if (networkSuccess) HttpStatusCode.OK else HttpStatusCode.InternalServerError,
                                headers = headersOf(HttpHeaders.ContentType, "application/json")
                            )
                        }
                        "/trending/movies/week" -> {
                            respond(
                                content = if (networkSuccess) MOCK_MOVIE_RESPONSE else MOCK_ERROR_RESPONSE,
                                status = if (networkSuccess) HttpStatusCode.OK else HttpStatusCode.InternalServerError,
                                headers = headersOf(HttpHeaders.ContentType, "application/json")
                            )
                        }
                        else -> {
                            respond(
                                content = if (networkSuccess) MOCK_MOVIE_RESPONSE else MOCK_ERROR_RESPONSE,
                                status = if (networkSuccess) HttpStatusCode.OK else HttpStatusCode.InternalServerError,
                                headers = headersOf(HttpHeaders.ContentType, "application/json")
                            )
                        }
                    }
                }
            }
        }

        fun expectSuccess(isSuccess: Boolean = true) {
            networkSuccess = isSuccess
        }
    }
}
