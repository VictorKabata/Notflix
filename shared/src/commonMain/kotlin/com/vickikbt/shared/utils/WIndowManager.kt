package com.vickikbt.shared.utils

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberWindowInfo(boxWithConstraintsScope: BoxWithConstraintsScope): WindowSize {
    return WindowSize.basedOnWidth(windowWidth = boxWithConstraintsScope.minWidth)
}

enum class WindowSize {
    COMPACT,
    MEDIUM,
    EXPANDED;

    // Factory method that creates an instance of the class based on window width
    companion object {
        fun basedOnWidth(windowWidth: Dp): WindowSize {
            return when {
                windowWidth < 600.dp -> COMPACT
                windowWidth < 840.dp -> MEDIUM
                else -> EXPANDED
            }
        }
    }
}
