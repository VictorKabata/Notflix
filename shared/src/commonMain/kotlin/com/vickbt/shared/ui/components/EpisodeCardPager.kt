package com.vickbt.shared.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberImagePainter
import com.vickbt.shared.domain.models.Episode
import com.vickbt.shared.utils.commonImageLoader

@Composable
fun EpisodeCardPager(
    modifier: Modifier = Modifier,
    episode: Episode?,
    onItemClick: (Episode?) -> Unit
) {
    val dominantTextColor by remember { mutableStateOf(Color.LightGray) }

    Column(modifier = Modifier.padding(horizontal = 4.dp)) {
        Card(modifier = modifier.clickable { onItemClick(episode) }) {
            Box(modifier = Modifier.fillMaxSize()) {
                //region Movie Cover Image
                commonImageLoader {
                    val painter = rememberImagePainter(episode?.poster ?: "")

                    Image(
                        modifier = Modifier.fillMaxSize(),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        painter = painter,
                        contentDescription = null
                    )
                }
                //endregion

                SmallFloatingActionButton(
                    modifier = Modifier.align(Alignment.Center),
                    shape = RoundedCornerShape(24.dp),
                    containerColor = dominantTextColor.copy(alpha = .4f),
                    onClick = { onItemClick(episode) },
                    elevation = FloatingActionButtonDefaults.elevation(2.dp)
                ) {
                    Image(
                        modifier = Modifier,
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        imageVector = Icons.Rounded.PlayArrow,
                        contentDescription = "Play episode",
                        alpha = .4f
                    )
                }
            }
        }

        Text(
            modifier = Modifier.width(170.dp),
            text = "Episode ${episode?.number}:",
            fontSize = 14.sp,
            maxLines = 1,
            style = MaterialTheme.typography.labelSmall,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            modifier = Modifier.width(170.dp),
            text = episode?.title ?: "Unknown episode",
            fontSize = 14.sp,
            maxLines = 1,
            style = MaterialTheme.typography.labelMedium,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
