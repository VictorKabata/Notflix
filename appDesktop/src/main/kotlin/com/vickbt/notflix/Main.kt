package com.vickbt.notflix

import androidx.compose.ui.window.application
import com.vickbt.notflix.ui.windows.MainWindow
import com.vickbt.shared.di.initKoin
import org.koin.core.Koin

lateinit var koin: Koin

fun main() {
    koin = initKoin(enableNetworkLogs = true).koin

    return application {
        Thread.currentThread().contextClassLoader = this.javaClass.classLoader
        MainWindow(applicationScope = this)
    }
}
