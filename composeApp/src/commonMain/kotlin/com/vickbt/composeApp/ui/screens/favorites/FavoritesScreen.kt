@file:OptIn(KoinExperimentalAPI::class)

package com.vickbt.composeApp.ui.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.kmpalette.loader.rememberNetworkLoader
import com.vickbt.composeApp.ui.components.MovieCardDescription
import com.vickbt.composeApp.ui.components.appbars.AppBar
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.title_favorites
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = koinViewModel<FavoritesViewModel>(),
    onNavigate: (String) -> Unit,
) {
    val favoritesUiState by viewModel.favoriteMoviesState.collectAsState()

    val networkLoader = rememberNetworkLoader(httpClient = koinInject())

    Scaffold(
        modifier = Modifier,
        topBar = { AppBar(title = stringResource(Res.string.title_favorites)) }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            if (favoritesUiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (!favoritesUiState.error.isNullOrEmpty()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Error:\n${favoritesUiState.error}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            } else {
                LazyColumn(
                    modifier = Modifier.align(Alignment.Center).fillMaxSize()
                        .padding(horizontal = 16.sdp),
                    verticalArrangement = Arrangement.spacedBy(6.sdp)
                ) {
                    items(items = favoritesUiState.favoriteMovies ?: emptyList()) { favoriteMovie ->
                        MovieCardDescription(
                            modifier = Modifier.fillMaxWidth(),
                            movie = favoriteMovie,
                            networkLoader = networkLoader
                        ) { movieDetails ->
                            onNavigate("/details/${movieDetails.id}")
                        }
                    }
                }
            }
        }
    }
}
