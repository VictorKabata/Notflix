package com.vickbt.composeApp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
internal fun Shapes(): Shapes {
    return Shapes(
        extraSmall = RoundedCornerShape(4.sdp),
        small = RoundedCornerShape(8.sdp),
        medium = RoundedCornerShape(12.sdp),
        large = RoundedCornerShape(16.sdp),
        extraLarge = RoundedCornerShape(24.sdp)
    )
}
