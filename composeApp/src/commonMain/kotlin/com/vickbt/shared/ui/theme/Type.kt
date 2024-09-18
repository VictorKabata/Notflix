package com.vickbt.shared.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.nunito
import org.jetbrains.compose.resources.Font

@Composable
internal fun Typography(): Typography {
    val Nunito = FontFamily(Font(Res.font.nunito))

    return Typography(
        titleLarge = TextStyle(
            fontFamily = Nunito,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
        ),

        titleMedium = TextStyle(
            fontFamily = Nunito,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
        ),

        bodyLarge = TextStyle(
            fontFamily = Nunito,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
        ),

        bodyMedium = TextStyle(
            fontFamily = Nunito,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.15.sp,
        ),

        labelSmall = TextStyle(
            fontFamily = Nunito,
            fontWeight = FontWeight.SemiBold
        )
    )
}
