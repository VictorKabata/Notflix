@file:OptIn(KoinExperimentalAPI::class)

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
import androidx.navigation.NavHostController
import com.vickbt.shared.ui.components.MovieCardDescription
import com.vickbt.shared.ui.components.appbars.AppBar
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.title_favorites
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun FavoritesScreen(
    navigator: NavHostController,
    viewModel: FavoritesViewModel = koinViewModel<FavoritesViewModel>()
) {
    val favoriteMovies = viewModel.favoriteMoviesState.collectAsState().value

    Scaffold(
        topBar = { AppBar(stringResource(Res.string.title_favorites)) }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .padding(bottom = 90.dp, start = 16.dp, end = 16.dp)
            ) {
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
