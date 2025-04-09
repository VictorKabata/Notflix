package com.vickbt.composeApp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.nunito
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.Font

@Composable
internal fun Typography(): Typography {
    val nunitoFontFamily = FontFamily(Font(Res.font.nunito))

    return Typography(
        headlineLarge = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 28.ssp,
            fontFamily = nunitoFontFamily
        ),
        headlineMedium = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.ssp,
            fontFamily = nunitoFontFamily
        ),
        headlineSmall = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 18.ssp,
            fontFamily = nunitoFontFamily
        ),
        titleLarge = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.ssp,
            fontFamily = nunitoFontFamily
        ),
        titleMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 16.ssp,
            fontFamily = nunitoFontFamily
        ),
        titleSmall = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.ssp,
            fontFamily = nunitoFontFamily
        ),
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.ssp,
            fontFamily = nunitoFontFamily
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.ssp,
            fontFamily = nunitoFontFamily
        ),
        bodySmall = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.ssp,
            fontFamily = nunitoFontFamily
        ),
        labelLarge = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 16.ssp,
            fontFamily = nunitoFontFamily
        ),
        labelMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.ssp,
            fontFamily = nunitoFontFamily
        ),
        labelSmall = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 12.ssp,
            fontFamily = nunitoFontFamily
        )
    )
}
