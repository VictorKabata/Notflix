package com.vickikbt.shared.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vickikbt.shared.domain.models.Actor

@Composable
fun ItemMovieCast(modifier: Modifier = Modifier, actor: Actor) {
    /*val painter = rememberImagePainter(
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
    }*/
}
