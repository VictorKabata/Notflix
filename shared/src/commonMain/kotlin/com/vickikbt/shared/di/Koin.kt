package com.vickikbt.shared.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule(enableNetworkLogs))
    }

// called by iOS etc
// fun initKoin() = initKoin(enableNetworkLogs = false) {}
