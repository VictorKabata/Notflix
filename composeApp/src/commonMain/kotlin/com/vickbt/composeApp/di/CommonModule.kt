package com.vickbt.composeApp.di

import com.vickbt.composeApp.data.datasources.FavoritesRepositoryImpl
import com.vickbt.composeApp.data.datasources.MovieDetailsRepositoryImpl
import com.vickbt.composeApp.data.datasources.MoviesRepositoryImpl
import com.vickbt.composeApp.data.datasources.SearchRepositoryImpl
import com.vickbt.composeApp.data.datasources.SettingsRepositoryImpl
import com.vickbt.composeApp.data.datasources.TvShowsRepositoryImpl
import com.vickbt.composeApp.domain.repositories.FavoritesRepository
import com.vickbt.composeApp.domain.repositories.MovieDetailsRepository
import com.vickbt.composeApp.domain.repositories.MoviesRepository
import com.vickbt.composeApp.domain.repositories.SearchRepository
import com.vickbt.composeApp.domain.repositories.SettingsRepository
import com.vickbt.composeApp.domain.repositories.TvShowsRepository
import com.vickbt.composeApp.domain.utils.Constants.BASE_URL
import com.vickbt.composeApp.domain.utils.Constants.URL_PATH
import com.vickbt.composeApp.ui.screens.details.DetailsViewModel
import com.vickbt.composeApp.ui.screens.favorites.FavoritesViewModel
import com.vickbt.composeApp.ui.screens.home.HomeViewModel
import com.vickbt.composeApp.ui.screens.main.MainViewModel
import com.vickbt.composeApp.ui.screens.search.SearchViewModel
import com.vickbt.composeApp.ui.screens.settings.SettingsViewModel
import com.vickbt.shared.BuildKonfig
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
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
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
    single<TvShowsRepository> { TvShowsRepositoryImpl(httpClient = get()) }
    single<MovieDetailsRepository> {
        MovieDetailsRepositoryImpl(httpClient = get(), appDatabase = get())
    }
    single<FavoritesRepository> { FavoritesRepositoryImpl(appDatabase = get()) }
    single<SearchRepository> { SearchRepositoryImpl(httpClient = get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(dataStore = get()) }

    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::FavoritesViewModel)
}

expect fun platformModule(): Module
