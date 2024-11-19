@file:OptIn(ExperimentalLayoutApi::class, ExperimentalLayoutApi::class)

package com.vickbt.composeApp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vickbt.composeApp.domain.utils.Enums
import com.vickbt.composeApp.utils.titleCase

@Composable
fun FilterHome(
    modifier: Modifier = Modifier,
    genres: List<String> = listOf(),
    onFilterClicked: (Enums.ShowType?) -> Unit = {},
    onCategoriesClicked: (String) -> Unit = {}
) {

    var selectedShowType by remember { mutableStateOf<Enums.ShowType?>(null) }

    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(visible = selectedShowType != null) {
            FloatingActionButton(
                modifier = Modifier.size(36.dp),
                shape = CircleShape,
                onClick = { selectedShowType = null }) {
                Icon(imageVector = Icons.Rounded.Close, contentDescription = null)
            }
        }

        AnimatedVisibility(visible = (selectedShowType == null) || selectedShowType == Enums.ShowType.TV_SHOW) {
            SuggestionChip(
                modifier = Modifier.padding(vertical = 8.dp),
                onClick = {
                    selectedShowType = Enums.ShowType.TV_SHOW
                    onFilterClicked(selectedShowType)
                },
                label = { Text("TV Shows") },
                shape = RoundedCornerShape(16.dp)
            )
        }

        AnimatedVisibility(visible = (selectedShowType == null) || selectedShowType == Enums.ShowType.MOVIE) {
            SuggestionChip(
                modifier = Modifier.padding(vertical = 8.dp),
                onClick = {
                    selectedShowType = Enums.ShowType.MOVIE
                    onFilterClicked(selectedShowType)
                },
                label = { Text("Movies") },
                shape = RoundedCornerShape(16.dp)
            )
        }

        AssistChip(
            modifier = Modifier.padding(vertical = 8.dp),
            onClick = { onCategoriesClicked("") },
            label = { Text("Categories") },
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                Icon(imageVector = Icons.Rounded.ArrowDropDown, contentDescription = null)
            }
        )
    }
}
