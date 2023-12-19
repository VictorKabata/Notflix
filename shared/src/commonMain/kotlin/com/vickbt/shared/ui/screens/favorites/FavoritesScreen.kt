package com.vickbt.shared.ui.screens.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.vickbt.shared.ui.components.appbars.AppBar
import io.github.aakira.napier.Napier
import org.koin.compose.koinInject

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = koinInject()) {
    Scaffold(
        topBar = { AppBar("Favorites") }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            val favoriteMovies = viewModel.favoriteMoviesState.collectAsState().value

            Napier.e(tag = "VicKbt", message = "Favorite movies: $favoriteMovies")
        }
    }
}
