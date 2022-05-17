package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import ui.navigation.NavigationItem
import ui.theme.Gray
import ui.theme.PrimaryColor
import utils.koil

@Composable
fun NavigationRailBar(modifier: Modifier = Modifier, navRailItems: List<NavigationItem>) {

    NavigationRail(
        modifier = modifier.fillMaxHeight().alpha(0.95F),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        contentColor = PrimaryColor
    ) {
        navRailItems.forEachIndexed { index, item ->
            var selectedItem by remember { mutableStateOf(0) }

            NavigationRailItem(
                icon = {
                    val image =
                        koil(url = "https://github.com/JetBrains/compose-jb/raw/master/artwork/idea-logo.svg")
                    image?.let {
                        Image(
                            bitmap = it,
                            contentDescription = ""
                        )
                    }
                    /*Icon(
                         painter = pain,
                        contentDescription = item.title
                    )*/
                },
                label = { Text(text = item.title) },
                selectedContentColor = PrimaryColor,
                unselectedContentColor = Gray,
                alwaysShowLabel = selectedItem == index,
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    println("Clicked: ${item.title}")
                }
            )
        }
    }
}
