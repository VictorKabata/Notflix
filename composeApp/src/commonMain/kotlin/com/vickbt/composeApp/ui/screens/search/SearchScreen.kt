@file:OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)

package com.vickbt.composeApp.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import app.cash.paging.compose.collectAsLazyPagingItems
import com.vickbt.composeApp.ui.components.MovieCardPortrait
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.back
import com.vickbt.shared.resources.title_search
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel<SearchViewModel>(),
    onNavigate: (String?) -> Unit,
) {
    val searchUiState by viewModel.searchUiState.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var activeState by remember { mutableStateOf(true) }

    SearchBar(
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface),
        query = searchQuery,
        onQueryChange = { searchQuery = it },
        onSearch = { viewModel.searchMovie(movieName = it) },
        active = activeState,
        onActiveChange = { activeState = it },
        placeholder = {
            Text(
                text = stringResource(Res.string.title_search),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
        },
        leadingIcon = {
            if (activeState) {
                IconButton(onClick = {
                    activeState = true
                    searchQuery = ""
                    onNavigate(null)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = stringResource(Res.string.back)
                    )
                }
            } else {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
            }
        },
        trailingIcon = {
            if (activeState) {
                IconButton(onClick = {
                    if (searchQuery.isNotEmpty()) searchQuery = "" else activeState = true
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
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            } else {
                searchUiState.movieResults?.let { movieResults ->
                    val searchResults = movieResults.collectAsLazyPagingItems()

                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = GridCells.Adaptive(minSize = 80.sdp),
                        horizontalArrangement = Arrangement.spacedBy(
                            8.sdp,
                            Alignment.CenterHorizontally
                        ),
                        verticalArrangement = Arrangement.spacedBy(8.sdp),
                        contentPadding = PaddingValues(vertical = 8.sdp, horizontal = 16.sdp)
                    ) {
                        items(searchResults.itemCount) { index ->
                            searchResults[index]?.let { movies ->
                                MovieCardPortrait(
                                    modifier = Modifier,
                                    movie = movies,
                                    onItemClick = { movie ->
                                        onNavigate("/details/${movie.id}")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
