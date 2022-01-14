package com.vickikbt.notflix.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun NotflixText(modifier: Modifier = Modifier, text: String, style: TextStyle) {
    Text(
        text = text,
        modifier = modifier,
        style = style
    )
}
