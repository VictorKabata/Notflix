package com.vickikbt.shared.di

import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {

    /*single { Settings() }*/

    /**
     * Creates a http client for Ktor that is provided to the
     * API client via constructor injection
     */
    /*single {
        HttpClient {
            defaultRequest {
                header("Authorization", TokenInterceptor().invoke())
            }

            install(Logging) {
                level = LogLevel.HEADERS
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.e(tag = "Http Client", message = message)
                    }
                }
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }*/
    /*single<ApiService> { ApiServiceImpl(httpClient = get()) }*/

    /*single { AccessTokenDao(databaseDriverFactory = get()) }*/
    /*single { DailyGoalDao(databaseDriverFactory = get()) }*/

    /*single<AuthRepository> { AuthRepositoryImpl(apiService = get(), accessTokenDao = get()) }
    single<DateTimeRepository> { DateTimeRepositoryImpl() }
    single<SummariesRepository> { SummariesRepositoryImpl(apiService = get(), dailyGoalDao = get()) }*/
}

expect fun platformModule(): Module
