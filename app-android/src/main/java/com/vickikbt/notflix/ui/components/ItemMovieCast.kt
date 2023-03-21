package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.google.accompanist.placeholder.placeholder
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.theme.TextSecondary
import com.vickikbt.notflix.util.loadImage
import com.vickikbt.shared.domain.models.Actor

@Composable
fun ItemMovieCast(modifier: Modifier = Modifier, actor: Actor, isLoading: Boolean = true) {
    val painter = rememberImagePainter(
        data = actor.profilePath?.loadImage()
    ) {
        crossfade(true)
        transformations(CircleCropTransformation())
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        Image(
            modifier = Modifier
                .placeholder(visible = isLoading, color = Color.Gray.copy(.8f))
                .size(80.dp),
            painter = painter,
            contentDescription = stringResource(id = R.string.cast)
        )

        Text(
            modifier = Modifier
                .placeholder(visible = isLoading, color = Color.Gray)
                .width(78.dp),
            text = actor.name ?: stringResource(R.string.unknown_actor),
            style = MaterialTheme.typography.h6,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 1,
            color = MaterialTheme.colors.onSurface
        )

        Text(
            modifier = Modifier
                .placeholder(visible = isLoading, color = Color.Gray)
                .width(77.dp),
            text = actor.character ?: stringResource(R.string.unknown_character),
            style = MaterialTheme.typography.h4,
            fontSize = 12.sp,
            color = TextSecondary,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}
