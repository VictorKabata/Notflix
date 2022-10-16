package com.vickikbt.notflix.ui.components.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PreferencesGroup(
    modifier: Modifier = Modifier,
    title: String? = null,
    isLast: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!title.isNullOrEmpty()) {
                Spacer(modifier = Modifier.weight(2f))

                Text(
                    modifier = Modifier
                        .weight(8f)
                        .padding(horizontal = 12.dp),
                    text = title,
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.primary,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )
            }
        }

        Spacer(modifier = Modifier.height(2.dp))

        Column(content = content, verticalArrangement = Arrangement.spacedBy(2.dp))

        if (!isLast) Divider(color = Color.Gray.copy(alpha = 0.7f), thickness = 1.dp)
    }
}
