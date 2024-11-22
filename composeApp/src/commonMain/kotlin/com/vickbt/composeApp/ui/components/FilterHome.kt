package com.vickbt.composeApp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vickbt.composeApp.domain.utils.Enums
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.categories
import com.vickbt.shared.resources.movies
import com.vickbt.shared.resources.tv_shows
import org.jetbrains.compose.resources.stringResource

@Composable
fun FilterHome(
    modifier: Modifier = Modifier,
    onFilterClicked: (Enums.ShowType?) -> Unit = {},
    onCategoriesClicked: () -> Unit = {}
) {

    var selectedShowType by remember { mutableStateOf<Enums.ShowType?>(null) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(visible = selectedShowType != null, enter = fadeIn(), exit = fadeOut()) {
            FloatingActionButton(
                modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.onSurface, CircleShape)
                    .size(32.dp),
                shape = CircleShape,
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onSurface,
                onClick = { selectedShowType = null }) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = Icons.Rounded.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        AnimatedVisibility(visible = (selectedShowType == null) || selectedShowType == Enums.ShowType.TV_SHOW) {
            SuggestionChip(
                onClick = {
                    selectedShowType = Enums.ShowType.TV_SHOW
                    onFilterClicked(selectedShowType)
                },
                label = {
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp),
                        text = stringResource(Res.string.tv_shows),
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                },
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.onSurface)
            )
        }

        AnimatedVisibility(visible = (selectedShowType == null) || selectedShowType == Enums.ShowType.MOVIE) {
            SuggestionChip(
                onClick = {
                    selectedShowType = Enums.ShowType.MOVIE
                    onFilterClicked(selectedShowType)
                },
                label = {
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp),
                        text = stringResource(Res.string.movies),
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                },
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.onSurface)
            )
        }

        AssistChip(
            onClick = { onCategoriesClicked() },
            label = {
                Text(
                    modifier = Modifier.padding(vertical = 2.dp),
                    text = stringResource(Res.string.categories),
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            },
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            },
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.onSurface)
        )
    }
}
