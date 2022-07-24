package ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.vickikbt.shared.presentation.presenters.SharedSettingsPresenter
import koin
import ui.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController, viewModel: SharedSettingsPresenter = koin.get()) {

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Settings",
            fontSize = 24.sp,
            color = MaterialTheme.colors.onSurface
        )
    }

}
