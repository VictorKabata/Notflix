package com.vickikbt.shared.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.shared.ui.theme.Golden

@Composable
fun MovieRatingSection(popularity: String?, voteAverage: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //region Popularity
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier,
                text = if (popularity.isNullOrEmpty()) "N/A" else popularity,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 42.sp,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Text(
                text = "Popularity",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        //endregion

        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(2.dp),
            color = MaterialTheme.colorScheme.onSurface
        )

        //region Rating
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.Rounded.Star,
                tint = Golden,
                contentDescription = "Rating"
            )

            Text(
                modifier = Modifier,
                text = if (voteAverage.isNullOrEmpty()) "N/A" else "$voteAverage/5.0",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        //endregion
    }
}
