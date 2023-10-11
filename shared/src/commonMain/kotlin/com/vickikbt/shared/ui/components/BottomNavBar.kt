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
import com.vickikbt.shared.presentation.ui.navigation.NavigationItem
import com.vickikbt.shared.ui.theme.Gray
import com.vickikbt.shared.ui.theme.PrimaryColor
import com.vickikbt.shared.ui.theme.White
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navigator: Navigator,
    bottomNavItems: List<NavigationItem>
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = .85f)
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
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Gray
                ),
                selected = isSelected,
                onClick = {
                    if (item.route != currentDestination) navigator.navigate(route = item.route)
                }
            )
        }
    }
}
