package com.vickbt.shared.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickbt.shared.domain.models.Season

@Composable
fun SeasonsDropDown(
    modifier: Modifier,
    content: List<Season>,
    text: Int? = content.first().number,
    isExpanded: Boolean = false,
    onItemClicked: (Season) -> Unit
) {
    var seasonNumber by remember { mutableStateOf(text) }
    var expanded by remember { mutableStateOf(isExpanded) }

    Box(modifier = modifier) {
        Button(
            modifier = Modifier.padding(vertical = 8.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = { expanded = !expanded }) {
            Text(
                text = "Sn$seasonNumber",
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Icon(
                imageVector = Icons.Rounded.ArrowDropDown,
                contentDescription = "Dropdown Icon",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            content.forEach {
                DropdownMenuItem(
                    onClick = {
                        expanded = !expanded
                        seasonNumber = it.number
                        onItemClicked(it)
                    },
                    text = {
                        Text(
                            "Season ${it.number}",
                            maxLines = 1,
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    })
            }
        }
    }
}
