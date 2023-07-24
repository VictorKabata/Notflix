package com.vickikbt.shared.di

import com.vickikbt.shared.BuildKonfig
import com.vickikbt.shared.data.datasources.FavoritesRepositoryImpl
import com.vickikbt.shared.data.datasources.MovieDetailsRepositoryImpl
import com.vickikbt.shared.data.datasources.MoviesRepositoryImpl
import com.vickikbt.shared.data.datasources.SettingsRepositoryImpl
import com.vickikbt.shared.domain.repositories.FavoritesRepository
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.domain.repositories.SettingsRepository
import com.vickikbt.shared.domain.utils.Constants.BASE_URL
import com.vickikbt.shared.domain.utils.Constants.URL_PATH
import com.vickikbt.shared.presentation.presenters.SharedDetailsPresenter
import com.vickikbt.shared.presentation.presenters.SharedFavouritesPresenter
import com.vickikbt.shared.presentation.presenters.SharedHomePresenter
import com.vickikbt.shared.presentation.presenters.SharedMainPresenter
import com.vickikbt.shared.presentation.presenters.SharedSettingsPresenter
import com.vickikbt.shared.presentation.ui.screens.home.HomeViewModel
import com.vickikbt.shared.utils.getAppLanguage
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
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
import org.koin.dsl.module

fun commonModule(enableNetworkLogs: Boolean) = module {
    /**
     * Creates a http client for Ktor that is provided to the
     * API client via constructor injection
     */
    single {
        HttpClient {
            expectSuccess = true
            addDefaultResponseValidation()

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                    path(URL_PATH)
                    parameters.append("api_key", BuildKonfig.API_KEY)
                    parameters.append("language", getAppLanguage(settingsPresenter = get()))
                }
            }

            if (enableNetworkLogs) {
                install(Logging) {
                    level = LogLevel.HEADERS
                    logger = object : Logger {
                        override fun log(message: String) {
                            Napier.i(tag = "Http Client", message = message)
                        }
                    }
                }.also {
                    Napier.base(DebugAntilog())
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

    single<MoviesRepository> { MoviesRepositoryImpl(httpClient = get()) }
    single<MovieDetailsRepository> { MovieDetailsRepositoryImpl(httpClient = get()) }
    single<FavoritesRepository> { FavoritesRepositoryImpl(httpClient = get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(observableSettings = get()) }

    factoryOf(::SharedMainPresenter)
    factoryOf(::SharedHomePresenter)
    factoryOf(::SharedDetailsPresenter)
    factoryOf(::SharedFavouritesPresenter)
    factoryOf(::SharedSettingsPresenter)

    factoryOf(::HomeViewModel)
}

expect fun platformModule(): Module
