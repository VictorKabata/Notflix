package com.vickbt.composeApp.ui.components.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun PreferencesGroup(
    modifier: Modifier = Modifier,
    title: String? = null,
    isLast: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier) {
        if (!title.isNullOrEmpty()) {
            Text(
                modifier = Modifier.padding(horizontal = 12.sdp, vertical = 4.sdp),
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
        }

        Spacer(modifier = Modifier.height(2.sdp))

        Column(content = content, verticalArrangement = Arrangement.spacedBy(2.sdp))

        if (!isLast) HorizontalDivider(color = Color.Gray.copy(alpha = 0.7f), thickness = 1.sdp)
    }
}
