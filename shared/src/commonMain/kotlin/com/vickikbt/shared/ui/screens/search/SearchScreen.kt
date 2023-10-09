package com.vickikbt.shared.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vickikbt.shared.ui.components.MovieCardPortrait
import com.vickikbt.shared.ui.components.appbars.AppBar
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.koinInject

@Composable
fun SearchScreen(navigator: Navigator, viewModel: SearchViewModel = koinInject()) {

    val searchUiState = viewModel.searchUiState.collectAsState().value

    Scaffold(
        topBar = { AppBar("Search") },
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            if (searchUiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (!searchUiState.error.isNullOrEmpty()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Error:\n${searchUiState.error}",
                    textAlign = TextAlign.Center
                )
            } else {
                if (searchUiState.movieResults.isNullOrEmpty()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Movie not found!",
                        textAlign = TextAlign.Center
                    )
                } else {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize()
                            // .align(Alignment.Center)
                            .background(Color.Red),
                        columns = GridCells.Adaptive(minSize = 150.dp),
                        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
                    ) {
                        items(searchUiState.movieResults) { item ->
                            MovieCardPortrait(
                                modifier = Modifier.padding(vertical = 4.dp),
                                movie = item,
                                onItemClick = { movie ->
                                    navigator.navigate("/details/${movie.id}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
