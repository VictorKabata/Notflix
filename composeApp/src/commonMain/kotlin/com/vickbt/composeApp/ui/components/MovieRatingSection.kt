package com.vickbt.composeApp.ui.components

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vickbt.composeApp.ui.theme.Golden
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.popularity
import com.vickbt.shared.resources.rating
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieRatingSection(modifier: Modifier = Modifier, popularity: String?, voteAverage: String?) {
    Row(
        modifier = modifier
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
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Text(
                text = stringResource(Res.string.popularity),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        //endregion

        VerticalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .width(2.sdp),
            color = MaterialTheme.colorScheme.onSurface
        )

        //region Rating
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(48.sdp),
                imageVector = Icons.Rounded.Star,
                tint = Golden,
                contentDescription = stringResource(Res.string.rating)
            )

            Text(
                modifier = Modifier,
                text = if (voteAverage.isNullOrEmpty()) "N/A" else "$voteAverage/5.0",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        //endregion
    }
}
