package com.vickikbt.notflix.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.vickikbt.notflix.R

val fonts = FontFamily(
    Font(R.font.roboto_regular, weight = FontWeight.Normal),
    Font(R.font.roboto_medium, weight = FontWeight.Medium),
    Font(R.font.roboto_bold, weight = FontWeight.Bold),
    Font(R.font.roboto_black, weight = FontWeight.Black)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal
    ),

    h4 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium
    ),

    h5 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold
    ),

    h6 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Black
    )
)
