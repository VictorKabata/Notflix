package com.vickikbt.notflix.ui.components.AppBars

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppBar(title: String, navigationIcon: Painter? = null, onClickNavigationIcon: () -> Unit = {}) {

    TopAppBar(backgroundColor = MaterialTheme.colors.surface, elevation = 0.dp) {
        if (navigationIcon != null) {
            IconButton(modifier = Modifier
                .size(40.dp),
                onClick = { onClickNavigationIcon() }) {
                Icon(
                    painter = navigationIcon,
                    contentDescription = "Navigation icon",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }

        Text(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
            text = title,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h6,
            fontSize = 28.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}