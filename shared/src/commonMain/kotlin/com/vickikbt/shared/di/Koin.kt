package com.vickikbt.shared.di

import com.vickikbt.shared.presentation.ui.screens.home.HomeViewModel
import com.vickikbt.shared.presentation.ui.screens.settings.SettingsViewModel
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = true, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(platformModule(), commonModule(enableNetworkLogs = enableNetworkLogs))
    }

// called by iOS etc
// fun initKoin() = initKoin(enableNetworkLogs = false) {}

fun KoinApplication.Companion.start(): KoinApplication = initKoin { }

val Koin.homeViewModel: HomeViewModel
    get() = get()

val Koin.settingsViewModel: SettingsViewModel
    get() = get()

/*val Koin.detailsPresenter: SharedDetailsPresenter
    get() = get()
val Koin.settingsPresenter: SharedSettingsPresenter
    get() = get()
val Koin.favoritesPresenter: SharedFavouritesPresenter
    get() = get()*/
