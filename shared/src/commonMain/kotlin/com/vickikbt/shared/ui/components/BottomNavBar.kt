package com.vickikbt.shared.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.vickikbt.shared.presentation.ui.navigation.NavigationItem
import com.vickikbt.shared.presentation.ui.theme.Gray
import com.vickikbt.shared.presentation.ui.theme.PrimaryColor
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navigator: Navigator,
    bottomNavItems: List<NavigationItem>
) {
    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .alpha(0.95F),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 16.dp
    ) {
        bottomNavItems.iterator().forEach { item ->

            val currentDestination =
                navigator.currentEntry.collectAsState(null).value?.route?.route
            val isSelected = item.route == currentDestination

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon!!,
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryColor,
                    selectedTextColor = PrimaryColor,
                    unselectedIconColor = Gray,
                    unselectedTextColor = Gray
                ),
                selected = isSelected,
                onClick = {
                    navigator.navigate(route = item.route)
                }
            )
        }
    }
}
