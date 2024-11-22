package com.vickbt.composeApp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.vickbt.composeApp.ui.navigation.NavigationItem
import com.vickbt.composeApp.ui.theme.Gray
import org.jetbrains.compose.resources.stringResource

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    bottomNavItems: List<NavigationItem>
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = .85f)
    ) {
        bottomNavItems.iterator().forEach { item ->

            val currentDestination =
                navHostController.currentBackStackEntryAsState().value?.destination?.route
            val isSelected = item.route == currentDestination

            NavigationBarItem(
                icon = {
                    item.icon?.let {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = stringResource(item.title)
                        )
                    }
                },
                label = {
                    Text(
                        text = stringResource(item.title),
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Gray
                ),
                selected = isSelected,
                onClick = {
                    if (item.route != currentDestination) navHostController.navigate(route = item.route)
                }
            )
        }
    }
}
