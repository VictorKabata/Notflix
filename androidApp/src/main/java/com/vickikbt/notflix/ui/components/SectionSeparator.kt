package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.theme.DarkPrimaryColor

@Composable
fun SectionSeparator(modifier: Modifier = Modifier, sectionTitle: String, onItemClick: () -> Unit) {
    Row(
        modifier = modifier.clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
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
