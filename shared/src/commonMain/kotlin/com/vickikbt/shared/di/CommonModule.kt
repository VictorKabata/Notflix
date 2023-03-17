package com.vickikbt.shared.di

import com.vickikbt.shared.data.cache.multiplatformsettings.PreferenceManager
import com.vickikbt.shared.data.cache.sqldelight.daos.MovieDao
import com.vickikbt.shared.data.datasources.FavoriteMovieRepositoryImpl
import com.vickikbt.shared.data.datasources.MovieDetailsRepositoryImpl
import com.vickikbt.shared.data.datasources.MoviesRepositoryImpl
import com.vickikbt.shared.data.datasources.SettingsRepositoryImpl
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.domain.repositories.FavoritesRepository
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.domain.repositories.SettingsRepository
import com.vickikbt.shared.domain.utils.Constants.API_KEY
import com.vickikbt.shared.domain.utils.Constants.BASE_URL
import com.vickikbt.shared.domain.utils.Constants.URL_PATH
import com.vickikbt.shared.presentation.presenters.SharedDetailsPresenter
import com.vickikbt.shared.presentation.presenters.SharedFavouritesPresenter
import com.vickikbt.shared.presentation.presenters.SharedHomePresenter
import com.vickikbt.shared.presentation.presenters.SharedMainPresenter
import com.vickikbt.shared.presentation.presenters.SharedSettingsPresenter
import com.vickikbt.shared.utils.getAppLanguage
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun commonModule(enableNetworkLogs: Boolean) = module {
    /**
     * Multiplatform-Settings
     */
    singleOf(::PreferenceManager)

    /**
     * Creates a http client for Ktor that is provided to the
     * API client via constructor injection
     */
    single {
        HttpClient(engineFactory = CIO) {
            expectSuccess = true
            addDefaultResponseValidation()

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                    path(URL_PATH)
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

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
        }
    }
    singleOf(::ApiService)

    singleOf(::MovieDao)

    single<FavoritesRepository> { FavoriteMovieRepositoryImpl(movieDao = get()) }
    single<MovieDetailsRepository> { MovieDetailsRepositoryImpl(apiService = get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(apiService = get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(preferenceManager = get()) }

    factoryOf(::SharedMainPresenter)
    factoryOf(::SharedHomePresenter)
    factoryOf(::SharedDetailsPresenter)
    factoryOf(::SharedFavouritesPresenter)
    factoryOf(::SharedSettingsPresenter)
}

expect fun platformModule(): Module
