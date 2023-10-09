package com.vickikbt.shared.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vickikbt.shared.ui.components.MovieCardPortrait
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navigator: Navigator, viewModel: SearchViewModel = koinInject()) {

    val searchUiState = viewModel.searchUiState.collectAsState().value

    var searchQuery by remember { mutableStateOf("") }
    var activeState by remember { mutableStateOf(false) }

    SearchBar(
        query = searchQuery,
        onQueryChange = { searchQuery = it },
        onSearch = { viewModel.searchMovie(movieName = it) },
        active = activeState,
        onActiveChange = { activeState = it },
        placeholder = {
            Text("Search")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
        },
        trailingIcon = {
            if (activeState) {
                IconButton(onClick = {
                    if (searchQuery.isNotEmpty()) searchQuery = "" else activeState = false
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Close search"
                    )
                }
            } else null
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
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
                        modifier = Modifier.fillMaxSize()//.padding(paddingValues)
                            .align(Alignment.Center),
                        columns = GridCells.Adaptive(minSize = 150.dp),
                        contentPadding = PaddingValues(bottom = 90.dp),
                        horizontalArrangement = Arrangement.Center
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
