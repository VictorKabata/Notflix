package com.vickbt.shared.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
actual fun systemBarsUiController(statusBarColor: Color, navBarColor: Color) {
    val useDarkIcon = !isSystemInDarkTheme()

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcon
        )
        systemUiController.setSystemBarsColor(color = statusBarColor)
        systemUiController.setNavigationBarColor(color = navBarColor)
    }
}
