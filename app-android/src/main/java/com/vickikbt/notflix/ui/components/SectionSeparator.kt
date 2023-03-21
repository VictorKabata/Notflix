package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.placeholder

@Composable
fun SectionSeparator(
    modifier: Modifier = Modifier,
    sectionTitle: String,
    isLoading: Boolean,
    onItemClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.placeholder(visible = isLoading, color = Color.Gray),
            text = sectionTitle,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h6,
            fontSize = 20.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        /*Text(
            text = stringResource(id = R.string.view_all),
            color = DarkPrimaryColor,
            style = MaterialTheme.typography.h5,
            fontSize = 14.sp
        )*/
    }
}
