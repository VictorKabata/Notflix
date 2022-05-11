package com.vickikbt.notflix.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import com.vickikbt.notflix.ui.theme.DarkPrimaryColor
import com.vickikbt.notflix.ui.theme.PrimaryColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

sealed class NetState {
    object NetworkOn : NetState()
    object NetworkOff : NetState()
    object NetworkUnknown : NetState()
}

@Composable
fun ChangeSystemBarColorOnNetChange(
    key: NetState,
    systemUiController: SystemUiController,
    darkTheme: Boolean = isSystemInDarkTheme(),
    color: Color = MaterialTheme.colors.surface
) {
    LaunchedEffect(key1 = key) {
        when (key) {
            NetState.NetworkOff -> {
                this.launch {
                    systemUiController.setSystemBarsColor(color = if (darkTheme) DarkPrimaryColor else PrimaryColor)
                    delay(3000L)
                    systemUiController.setSystemBarsColor(color = color)
                }
            }
            NetState.NetworkOn -> {}
            NetState.NetworkUnknown -> {}
        }
    }
}
