package com.vickikbt.shared.data.network

import com.goncalossilva.resources.Resource
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class MockNotflixServer {

    private var httpStatusCode: HttpStatusCode = HttpStatusCode.OK

    private var responseContent: String? = null
    fun throwError(httpStatus: HttpStatusCode, response: String) {
        httpStatusCode = httpStatus
        responseContent = response
    }

    private val responseHeaders = headersOf(HttpHeaders.ContentType, "application/json")

    private val mockMoviesResponse =
        Resource("src/commonTest/resources/movies_response.json").readText()
    private val mockMoviesResponseError =
        Resource("src/commonTest/resources/movies_response_error.json").readText()
    private val mockMovieDetailsResponse =
        Resource("src/commonTest/resources/movie_details_response.json").readText()
    private val mockMovieCastResponse =
        Resource("src/commonTest/resources/movie_cast_response.json").readText()
    private val mockMovieSimilarResponse =
        Resource("src/commonTest/resources/movie_similar_response.json").readText()
    val mock500ErrorResponse =
        Resource("src/commonTest/resources/error_response.json").readText()

    val mockHttpClient = HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                when (request.url.encodedPath) {
                    "/movie/now_playing" -> {
                        respond(
                            content = responseContent ?: mockMoviesResponse,
                            status = httpStatusCode,
                            headers = responseHeaders
                        )
                    }
                    "/trending/movie/week" -> {
                        respond(
                            content = responseContent ?: mockMoviesResponse,
                            status = httpStatusCode,
                            headers = responseHeaders
                        )
                    }
                    "/movie/popular" -> {
                        respond(
                            content = responseContent ?: mockMoviesResponse,
                            status = httpStatusCode,
                            headers = responseHeaders
                        )
                    }
                    "/movie/upcoming" -> {
                        respond(
                            content = responseContent ?: mockMoviesResponse,
                            status = httpStatusCode,
                            headers = responseHeaders
                        )
                    }
                    "/movie/{id}" -> {
                        respond(
                            content = responseContent ?: mockMovieDetailsResponse,
                            status = httpStatusCode,
                            headers = responseHeaders
                        )
                    }
                    "/movie/{id}/credits" -> {
                        respond(
                            content = responseContent ?: mockMovieCastResponse,
                            status = httpStatusCode,
                            headers = responseHeaders
                        )
                    }
                    "/movie/{id}/similar" -> {
                        respond(
                            content = responseContent ?: mockMovieSimilarResponse,
                            status = httpStatusCode,
                            headers = responseHeaders
                        )
                    }
                    else -> {
                        respond(
                            content = responseContent ?: mockMoviesResponseError,
                            status = HttpStatusCode.InternalServerError,
                            headers = responseHeaders
                        )
                    }
                }
            }
        }

        expectSuccess = true
        addDefaultResponseValidation()

        defaultRequest { contentType(ContentType.Application.Json) }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("Mock Http Logs: $message")
                }
            }
        }
    }
}
