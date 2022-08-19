package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.theme.Gray
import com.vickikbt.notflix.ui.theme.TextSecondary
import io.github.aakira.napier.Napier

@Composable
fun MovieRatingSection(popularity: String?, voteAverage: String?) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .placeholder(
                visible = popularity.isNullOrEmpty(),
                color = Gray,
                highlight = PlaceholderHighlight.fade(highlightColor = TextSecondary)
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Napier.e("Popularity: $popularity")
        Napier.e("Vote Average: $voteAverage")

        //region Popularity
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (popularity.isNullOrEmpty()) "N/A" else popularity,
                style = MaterialTheme.typography.h6,
                fontSize = 42.sp,
                color = MaterialTheme.colors.onSurface,
            )

            Text(
                text = stringResource(id = R.string.popularity),
                style = MaterialTheme.typography.h6,
                fontSize = 18.sp,
                color = MaterialTheme.colors.onSurface,
            )
        }
        //endregion

        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(2.dp),
            color = MaterialTheme.colors.onSurface
        )

        //region Rating
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_rating_star),
                contentDescription = stringResource(R.string.rating)
            )

            Text(
                text = if (voteAverage.isNullOrEmpty()) "N/A" else "${voteAverage}/5.0",
                style = MaterialTheme.typography.h6,
                fontSize = 20.sp,
                color = MaterialTheme.colors.onSurface,
            )
        }
        //endregion
    }
}
