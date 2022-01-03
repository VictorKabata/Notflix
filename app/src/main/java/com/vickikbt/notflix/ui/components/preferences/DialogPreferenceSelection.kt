package com.vickikbt.notflix.ui.components.preferences

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogPreferenceSelection(
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    title: String,
    currentValue:String?=null,
    options: Array<String>,
    onNegativeClick: () -> Unit,
    onOptionSelected:(String)->Unit
) {

    if (showDialog) {
        Dialog(onDismissRequest = { onNegativeClick() }) {
            Card(
                elevation = 8.dp,
                shape = RoundedCornerShape(2.dp)
            ) {
                Column(modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)) {

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = title,
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 21.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        options.forEach { option ->

                            ItemPreferenceOption(optionText = option, selectedOption = option==currentValue) {
                                onOptionSelected(option)
                                onNegativeClick()
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            modifier = Modifier.clickable { onNegativeClick() },
                            text = "CANCEL",
                            style = MaterialTheme.typography.h4,
                            color = MaterialTheme.colors.primary,
                            fontSize = 16.sp,
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