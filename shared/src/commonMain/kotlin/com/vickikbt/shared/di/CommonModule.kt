package com.vickikbt.shared.di

import com.vickikbt.shared.data.cache.multiplatform_settings.PreferenceManager
import com.vickikbt.shared.data.cache.sqldelight.daos.MovieDao
import com.vickikbt.shared.data.data_sources.FavoriteMovieRepositoryImpl
import com.vickikbt.shared.data.data_sources.MovieDetailsRepositoryImpl
import com.vickikbt.shared.data.data_sources.MoviesRepositoryImpl
import com.vickikbt.shared.data.data_sources.SettingsRepositoryImpl
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.domain.repositories.FavoritesRepository
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.domain.repositories.SettingsRepository
import com.vickikbt.shared.domain.utils.Constants.API_KEY
import com.vickikbt.shared.domain.utils.Constants.BASE_URL
import com.vickikbt.shared.presentation.presenters.SharedDetailsPresenter
import com.vickikbt.shared.presentation.presenters.SharedFavouritesPresenter
import com.vickikbt.shared.presentation.presenters.SharedHomePresenter
import com.vickikbt.shared.presentation.presenters.SharedMainPresenter
import com.vickikbt.shared.presentation.presenters.SharedSettingsPresenter
import com.vickikbt.shared.utils.getAppLanguage
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.http.URLProtocol
import org.koin.core.module.Module
import org.koin.dsl.module

fun commonModule(enableNetworkLogs: Boolean) = module {

    /**
     * Multiplatform-Settings
     */
    single {
        PreferenceManager(observableSettings = get())
    }

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
                    parameters.append("language", getAppLanguage(settingsPresenter = get()))
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
    single { ApiService(httpClient = get()) }

    single { MovieDao(databaseDriverFactory = get()) }

    single<FavoritesRepository> { FavoriteMovieRepositoryImpl() }
    single<MovieDetailsRepository> { MovieDetailsRepositoryImpl(apiService = get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(apiService = get(), moviesDao = get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(preferenceManager = get()) }

    single { SharedMainPresenter(settingsRepository = get()) }
    single { SharedHomePresenter(moviesRepository = get()) }
    single { SharedDetailsPresenter(movieDetailsRepository = get()) }
    single { SharedFavouritesPresenter(favouritesRepository = get()) }
    single { SharedSettingsPresenter(settingsRepository = get()) }
}

expect fun platformModule(): Module
