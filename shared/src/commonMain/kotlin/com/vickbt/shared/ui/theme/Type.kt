package com.vickbt.shared.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import notflix.shared.generated.resources.Res
import notflix.shared.generated.resources.nunito
import org.jetbrains.compose.resources.Font

@Composable
internal fun Typography(): Typography {

    val Nunito = FontFamily(Font(Res.font.nunito))

    return Typography(
        titleLarge = TextStyle(
            fontFamily = Nunito,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
        ),

        titleMedium = TextStyle(
            fontFamily = Nunito,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
        ),

        bodyMedium = TextStyle(
            fontFamily = Nunito,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
        )
    )
}
