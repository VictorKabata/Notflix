package com.vickikbt.shared.di

import com.vickikbt.shared.data.data_sources.FavoriteMovieRepositoryImpl
import com.vickikbt.shared.data.data_sources.MovieDetailsRepositoryImpl
import com.vickikbt.shared.data.data_sources.MoviesRepositoryImpl
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.data.network.ApiServiceImpl
import com.vickikbt.shared.domain.repositories.FavoritesRepository
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.domain.utils.Constants.API_KEY
import com.vickikbt.shared.domain.utils.Constants.BASE_URL
import com.vickikbt.shared.presentation.viewmodels.SharedDetailsViewModel
import com.vickikbt.shared.presentation.viewmodels.SharedHomeViewModel
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import org.koin.core.module.Module
import org.koin.dsl.module

fun commonModule(enableNetworkLogs: Boolean) = module {

    /*single { Settings() }*/

    /**
     * Creates a http client for Ktor that is provided to the
     * API client via constructor injection
     */
    single {
        HttpClient {
            defaultRequest {
                url {
                    host = BASE_URL
                    url { protocol = URLProtocol.HTTPS }
                    parameters.append("api_key", API_KEY)
                }
            }

            if (enableNetworkLogs) {
                install(Logging) {
                    level = LogLevel.HEADERS
                    logger = object : Logger {
                        override fun log(message: String) {
                            Napier.e(tag = "Http Client", message = message)
                        }
                    }
                }
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
        }
    }
    single<ApiService> { ApiServiceImpl(httpClient = get()) }

    single<FavoritesRepository> { FavoriteMovieRepositoryImpl() }
    single<MovieDetailsRepository> { MovieDetailsRepositoryImpl(apiService = get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(apiService = get()) }

    single { SharedHomeViewModel(moviesRepository = get()) }
    single { SharedDetailsViewModel(movieDetailsRepository = get()) }
}

expect fun platformModule(): Module
