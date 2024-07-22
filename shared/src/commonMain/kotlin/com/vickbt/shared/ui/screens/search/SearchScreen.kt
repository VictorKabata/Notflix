package com.vickbt.shared.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vickbt.shared.ui.components.MovieCardPortrait
import com.vickbt.shared.utils.SearchUiState
import com.vickbt.shared.utils.WindowSize
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun SearchScreen(
    navigator: NavHostController,
    searchUiState: SearchUiState,
    windowSize: WindowSize = WindowSize.COMPACT
) {
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {
        if (searchUiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (!searchUiState.error.isNullOrEmpty()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Error:\n${searchUiState.error}",
                textAlign = TextAlign.Center
            )
        } else {
            searchUiState.movieResults?.let { movieResults ->
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize()
                        .padding(vertical = 12.dp, horizontal = 16.dp)
                        .align(Alignment.Center),
                    columns = if (windowSize == WindowSize.COMPACT) {
                        GridCells.Fixed(2)
                    } else {
                        GridCells.Adaptive(minSize = 150.dp)
                    },
                    contentPadding = PaddingValues(bottom = 90.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(movieResults) { item ->
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
