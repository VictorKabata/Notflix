package com.vickikbt.notflix.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.components.AppBar

@Composable
fun SettingsScreen(navController: NavController) {

    Scaffold(topBar = { AppBar(stringResource(id = R.string.title_settings)) }) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
            Column {
                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }

}
