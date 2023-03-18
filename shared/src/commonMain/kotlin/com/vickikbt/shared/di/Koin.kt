package com.vickikbt.shared.di

import com.vickikbt.shared.presentation.presenters.SharedDetailsPresenter
import com.vickikbt.shared.presentation.presenters.SharedFavouritesPresenter
import com.vickikbt.shared.presentation.presenters.SharedHomePresenter
import com.vickikbt.shared.presentation.presenters.SharedSettingsPresenter
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

val Koin.homePresenter: SharedHomePresenter
    get() = get()
val Koin.mainPresenter: SharedHomePresenter
    get() = get()
val Koin.detailsPresenter: SharedDetailsPresenter
    get() = get()
val Koin.settingsPresenter: SharedSettingsPresenter
    get() = get()
val Koin.favoritesPresenter: SharedFavouritesPresenter
    get() = get()
