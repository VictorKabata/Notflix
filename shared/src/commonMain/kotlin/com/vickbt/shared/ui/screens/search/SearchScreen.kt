@file:OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)

package com.vickbt.shared.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vickbt.shared.ui.components.MovieCardPortrait
import com.vickbt.shared.utils.WindowSize
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun SearchScreen(
    navigator: NavHostController,
    viewModel: SearchViewModel = koinViewModel<SearchViewModel>(),
    windowSize: WindowSize = WindowSize.COMPACT,
    mainPaddingValues: PaddingValues
) {

    val searchUiState by viewModel.searchUiState.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var activeState by remember { mutableStateOf(true) }

    SearchBar(
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)
            .padding(mainPaddingValues),
        query = searchQuery,
        onQueryChange = { searchQuery = it },
        onSearch = { viewModel.searchMovie(movieName = it) },
        active = activeState,
        onActiveChange = { activeState = it },
        placeholder = {
            Text(
                text = "Search Movie",
                fontSize = 18.sp,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
        },
        leadingIcon = {
            if (activeState) {
                IconButton(onClick = {
                    activeState = false
                    searchQuery = ""
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Go back"
                    )
                }
            } else {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
            }
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
            } else {
                null
            }
        },
        colors = SearchBarDefaults.colors(dividerColor = Color.LightGray)
    ) {

        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)
        ) {
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
                        modifier = Modifier.fillMaxSize(),
                        columns = if (windowSize == WindowSize.COMPACT) {
                            GridCells.Fixed(2)
                        } else {
                            GridCells.Adaptive(minSize = 150.dp)
                        },
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(items = movieResults) { item ->
                            MovieCardPortrait(
                                modifier = Modifier,
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
