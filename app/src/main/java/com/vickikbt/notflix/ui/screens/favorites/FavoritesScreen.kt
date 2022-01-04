package com.vickikbt.notflix.ui.screens.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.components.AppBar

@Composable
fun FavoritesScreen(navController: NavController) {

    Scaffold(topBar = { AppBar(stringResource(id = R.string.title_favorites)) }) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {

        }
    }
}
