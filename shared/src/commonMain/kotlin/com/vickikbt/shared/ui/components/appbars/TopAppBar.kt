package com.vickikbt.shared.ui.components.appbars

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, navigationIcon: Painter? = null, onClickNavigationIcon: () -> Unit = {}) {
    MediumTopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(MaterialTheme.colorScheme.surface),
        title = {}
    )
    /*{
            if (navigationIcon != null) {
                IconButton(
                    modifier = Modifier
                        .size(40.dp),
                    onClick = { onClickNavigationIcon() }
                ) {
                    Icon(
                        painter = navigationIcon,
                        contentDescription = "Navigation icon",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Text(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 28.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }*/
}
