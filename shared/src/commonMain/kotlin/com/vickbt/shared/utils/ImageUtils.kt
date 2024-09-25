package com.vickbt.shared.utils

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter

@Composable
fun ImageComponent(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    Image(
        modifier = modifier,
        painter = rememberAsyncImagePainter(
            model = imageUrl,
            imageLoader = LocalPlatformContext.current.getAsyncImageLoader()
        ),
        contentDescription = contentDescription,
        contentScale = contentScale,
        alignment = Alignment.Center
    )
}
