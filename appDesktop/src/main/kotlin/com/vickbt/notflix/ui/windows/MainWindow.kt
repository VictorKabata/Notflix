package com.vickbt.notflix.ui.windows

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.vickbt.shared.ui.screens.main.MainScreen
import moe.tlaster.precompose.PreComposeWindow

@Composable
fun MainWindow(applicationScope: ApplicationScope) {
    Window(
        onCloseRequest = { applicationScope.exitApplication() },
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
