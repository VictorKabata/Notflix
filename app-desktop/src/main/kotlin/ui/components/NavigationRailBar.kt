package ui.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ui.navigation.NavigationItem
import ui.screens.settings.SettingsScreen
import ui.theme.Gray
import ui.theme.PrimaryColor

@Composable
fun NavigationRailBar(
    navigator: Navigator = LocalNavigator.currentOrThrow,
    modifier: Modifier = Modifier,
    navRailItems: List<NavigationItem>
) {
    NavigationRail(
        modifier = modifier.fillMaxHeight().alpha(0.95F),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        header = {
            Icon(
                modifier = Modifier.size(42.dp),
                painter = painterResource("n_logo.png"),
                contentDescription = "Logo"
            )
        },
        contentColor = PrimaryColor
    ) {
        navRailItems.forEach { item ->
            NavigationRailItem(
                icon = {
                    item.icon?.let {
                        Icon(
                            painter = painterResource(it),
                            contentDescription = item.title
                        )
                    }
                },
                label = { Text(text = item.title) },
                selectedContentColor = PrimaryColor,
                unselectedContentColor = Gray,
                alwaysShowLabel = false,
                selected = false,
                onClick = { navigator.push(SettingsScreen()) }
            )
        }
    }
}
