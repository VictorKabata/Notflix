package com.vickbt.shared.ui.screens.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vickbt.shared.ui.components.MovieCardDescription
import com.vickbt.shared.ui.components.appbars.AppBar
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.koinInject

@Composable
fun FavoritesScreen(navigator: Navigator, viewModel: FavoritesViewModel = koinInject()) {
    val favoriteMovies = viewModel.favoriteMoviesState.collectAsState().value

    Scaffold(
        topBar = { AppBar("Favorites") }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(bottom = 90.dp)) {
                items(items = favoriteMovies.favoriteMovies ?: emptyList()) { favoriteMovie ->
                    MovieCardDescription(
                        modifier = Modifier.fillMaxWidth().height(260.dp).padding(vertical = 4.dp),
                        movie = favoriteMovie
                    ) { movieDetails ->
                        navigator.navigate("/details/${movieDetails.id}")
                    }
                }
            }
        }
    }
}
