package com.vickbt.composeApp.ui.components.preferences

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.window.Dialog
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun DialogPreferenceSelection(
    showDialog: Boolean,
    title: String,
    currentValue: String? = null,
    labels: List<String>,
    onDismissRequest: () -> Unit,
    onOptionSelected: (Int) -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = { onDismissRequest() }) {
            Card(
                elevation = CardDefaults.cardElevation(8.sdp),
                shape = MaterialTheme.shapes.extraSmall
            ) {
                Column(
                    modifier = Modifier.padding(16.sdp),
                    verticalArrangement = Arrangement.spacedBy(8.sdp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = title,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(4.sdp)) {
                        labels.forEachIndexed { index, option ->
                            ItemPreferenceOption(
                                optionText = option,
                                selectedOption = option == currentValue
                            ) {
                                onOptionSelected(index)
                                onDismissRequest()
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            modifier = Modifier.clickable { onDismissRequest() },
                            text = "Cancel".toUpperCase(Locale.current),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
