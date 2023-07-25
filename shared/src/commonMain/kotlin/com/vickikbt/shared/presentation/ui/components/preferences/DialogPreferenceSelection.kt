package com.vickikbt.shared.presentation.ui.components.preferences

import androidx.compose.runtime.Composable

@Composable
fun DialogPreferenceSelection(
    showDialog: Boolean,
    title: String,
    currentValue: String? = null,
    labels: List<String>,
    onNegativeClick: () -> Unit,
    onOptionSelected: (Int) -> Unit
) {
    /* ToDo
    if (showDialog) {
        Dialog(onDismissRequest = { onNegativeClick() }) {
            Card(
                elevation = 8.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = title,
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 22.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        labels.forEachIndexed { index, option ->

                            ItemPreferenceOption(
                                optionText = option,
                                selectedOption = option == currentValue
                            ) {
                                onOptionSelected(index)
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
                            text = stringResource(id = R.string.cancel).uppercase(Locale.getDefault()),
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
    }*/
}
