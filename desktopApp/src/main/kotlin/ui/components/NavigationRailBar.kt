package ui.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ui.navigation.NavigationItem
import ui.theme.Gray
import ui.theme.PrimaryColor

@Composable
fun NavigationRailBar(modifier: Modifier = Modifier, navRailItems: List<NavigationItem>) {

    NavigationRail(
        modifier = modifier.fillMaxHeight().alpha(0.95F),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        header = {
            Icon(modifier = Modifier.size(42.dp), painter = painterResource("n_logo.png"), contentDescription = "Logo")
        },
        contentColor = PrimaryColor
    ) {
        navRailItems.forEachIndexed { index, item ->
            var selectedItem by remember { mutableStateOf(0) }

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
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    println("Clicked: ${item.title}")
                }
            )
        }
    }
}
