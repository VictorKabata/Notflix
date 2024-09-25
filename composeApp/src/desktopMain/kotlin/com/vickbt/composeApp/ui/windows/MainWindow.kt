package com.vickbt.composeApp.ui.windows

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.vickbt.composeApp.di.initKoin
import com.vickbt.composeApp.ui.screens.main.MainScreen
import org.koin.core.Koin

lateinit var koin: Koin

fun main() {
    koin = initKoin(enableNetworkLogs = true).koin

    return application {
        Thread.currentThread().contextClassLoader = this.javaClass.classLoader

        Window(
            onCloseRequest = { this.exitApplication() },
            title = "Notflix",
            state = rememberWindowState(
                position = WindowPosition.Aligned(Alignment.Center),
                width = 1080.dp,
                height = 800.dp,
            )
        ) {
            MainScreen()
        }
    }
}
