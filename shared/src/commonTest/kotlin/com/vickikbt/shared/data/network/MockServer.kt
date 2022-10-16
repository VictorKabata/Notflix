package com.vickikbt.shared.data.network

import com.goncalossilva.resources.Resource
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlin.native.concurrent.ThreadLocal
import kotlinx.serialization.json.Json

@ThreadLocal
var networkSuccess: Boolean = true

object MockServer {

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

    val mockHttpClient = HttpClient(MockEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json {
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
                            content = if (networkSuccess) ByteReadChannel(mockMoviesResponse)
                            else ByteReadChannel(mockMoviesResponseError),
                            status = if (networkSuccess) HttpStatusCode.OK else HttpStatusCode.NotFound,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                    "/trending/movie/week" -> {
                        respond(
                            content = if (networkSuccess) ByteReadChannel(mockMoviesResponse)
                            else ByteReadChannel(mockMoviesResponseError),
                            status = if (networkSuccess) HttpStatusCode.OK else HttpStatusCode.NotFound,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                    "/movie/popular" -> {
                        respond(
                            content = if (networkSuccess) ByteReadChannel(mockMoviesResponse)
                            else ByteReadChannel(mockMoviesResponseError),
                            status = if (networkSuccess) HttpStatusCode.OK else HttpStatusCode.NotFound,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                    "/movie/upcoming" -> {
                        respond(
                            content = if (networkSuccess) ByteReadChannel(mockMoviesResponse)
                            else ByteReadChannel(mockMoviesResponseError),
                            status = if (networkSuccess) HttpStatusCode.OK else HttpStatusCode.NotFound,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                    "/movie/{id}" -> {
                        respond(
                            content = if (networkSuccess) ByteReadChannel(mockMovieDetailsResponse)
                            else ByteReadChannel(mockMoviesResponseError),
                            status = if (networkSuccess) HttpStatusCode.OK else HttpStatusCode.NotFound,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                    "/movie/{id}/credits" -> {
                        respond(
                            content = if (networkSuccess) ByteReadChannel(mockMovieCastResponse)
                            else ByteReadChannel(mockMoviesResponseError),
                            status = if (networkSuccess) HttpStatusCode.OK else HttpStatusCode.NotFound,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                    "/movie/{id}/similar" -> {
                        respond(
                            content = if (networkSuccess) ByteReadChannel(mockMovieSimilarResponse)
                            else ByteReadChannel(mockMoviesResponseError),
                            status = if (networkSuccess) HttpStatusCode.OK else HttpStatusCode.NotFound,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                    else -> {
                        respond(
                            content = ByteReadChannel(mockMoviesResponseError),
                            status = HttpStatusCode.InternalServerError,
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
