package ui.components.preferences

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.vickikbt.notflix.ui.components.preferences.ItemPreferenceOption
import java.util.*

@Composable
fun DialogPreferenceSelection(
    showDialog: Boolean,
    title: String,
    currentValue: String? = null,
    labels: List<String>,
    onNegativeClick: () -> Unit,
    onOptionSelected: (Int) -> Unit
) {

    if (showDialog) {

        Dialog(onCloseRequest = { onNegativeClick() }, undecorated = true, resizable = true) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colors.surface)
                    .padding(vertical = 16.dp, horizontal = 24.dp)
            ) {

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
                        text = ("Cancel").uppercase(Locale.getDefault()),
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
