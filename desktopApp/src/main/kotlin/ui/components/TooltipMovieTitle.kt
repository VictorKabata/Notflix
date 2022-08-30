@file:OptIn(ExperimentalFoundationApi::class)

package ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun TooltipMovieTitle(
    modifier: Modifier = Modifier,
    title: String?,
    content: @Composable () -> Unit
) {
    title?.let {
        TooltipArea(
            tooltip = {
                Surface(
                    modifier = Modifier.shadow(4.dp),
                    color = MaterialTheme.colors.surface.copy(alpha = .7F),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = title.uppercase(),
                        modifier = Modifier.padding(8.dp),
                        color = MaterialTheme.colors.onSurface
                    )
                }
            },
            modifier = modifier,
            delayMillis = 0,
            tooltipPlacement = TooltipPlacement.CursorPoint()
        ) {
            content()
        }
    }
}
