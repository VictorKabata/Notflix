package com.vickikbt.shared.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vickikbt.shared.presentation.ui.navigation.NavigationItem
import com.vickikbt.shared.presentation.ui.theme.Gray
import com.vickikbt.shared.presentation.ui.theme.PrimaryColor

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    // backStackEntryState: State<NavBackStackEntry?>,
    // navController: NavController,
    bottomNavItems: List<NavigationItem>
) {
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .alpha(0.95F),
        backgroundColor = MaterialTheme.colors.surface,
        cutoutShape = RoundedCornerShape(70),
        elevation = 16.dp
    ) {
        BottomNavigation(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = PrimaryColor
        ) {
            bottomNavItems.iterator().forEach { item ->
                val isSelected = false //item.route == backStackEntryState.value?.destination?.route

                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon!!,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(text = item.title) },
                    selectedContentColor = PrimaryColor,
                    unselectedContentColor = Gray,
                    alwaysShowLabel = true,
                    selected = isSelected,
                    onClick = {
                        /*navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route = route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }*/
                    }
                )
            }
        }
    }
}
