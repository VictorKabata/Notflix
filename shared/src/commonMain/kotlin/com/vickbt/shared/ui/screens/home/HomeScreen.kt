package com.vickbt.shared.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
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
import androidx.compose.runtime.LaunchedEffect
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
import com.vickbt.shared.ui.components.MovieCardLandscape
import com.vickbt.shared.ui.components.MovieCardPager
import com.vickbt.shared.ui.components.MovieCardPagerIndicator
import com.vickbt.shared.ui.components.MovieCardPortraitCompact
import com.vickbt.shared.ui.components.SectionSeparator
import com.vickbt.shared.ui.screens.search.SearchScreen
import com.vickbt.shared.ui.theme.DarkPrimaryColor
import com.vickbt.shared.utils.WindowSize
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigator: NavHostController,
    windowSize: WindowSize = WindowSize.COMPACT,
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    paddingValues: PaddingValues
) {
    val scrollState = rememberScrollState()

    val homeUiState = viewModel.homeUiState.collectAsState().value
    val searchUiState = viewModel.searchUiState.collectAsState().value

    var searchQuery by remember { mutableStateOf("") }
    var activeState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 6.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        //region Search
        SearchBar(
            modifier = Modifier.background(
                MaterialTheme.colorScheme.surface
            ).also {
                if (activeState) it.fillMaxWidth() else it.fillMaxWidth(.85f)
            },
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
                            imageVector = Icons.Rounded.ArrowBack,
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
            colors = SearchBarDefaults.colors(
                dividerColor = Color.LightGray,
                // inputFieldColors = TextFieldDefaults.colors()
            )
        ) {
            SearchScreen(
                navigator = navigator,
                searchUiState = searchUiState,
                windowSize = windowSize
            )
        }
        //endregion

        // region Home section
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (homeUiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (!homeUiState.error.isNullOrEmpty()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Error:\n${homeUiState.error}",
                    textAlign = TextAlign.Center
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //region Now Playing Movies
                    homeUiState.nowPlayingMovies?.let { nowPlayingMovies ->
                        val pagerState =
                            rememberPagerState(pageCount = { nowPlayingMovies.size })

                        HorizontalPager(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                            state = pagerState,
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            pageSpacing = 8.dp
                        ) { currentPage ->
                            MovieCardPager(
                                modifier = Modifier.fillMaxWidth().height(280.dp),
                                movie = nowPlayingMovies[currentPage]
                            ) { movie ->
                                navigator.navigate("/details/${movie.id}")
                            }
                        }

                        MovieCardPagerIndicator(
                            modifier = Modifier.padding(vertical = 6.dp),
                            pagerState = pagerState,
                            indicatorSize = 6.dp,
                            inactiveColor = Color.Gray,
                            activeColor = DarkPrimaryColor
                        )
                    }
                    //endregion

                    //region Trending Movies
                    homeUiState.trendingMovies?.let {
                        SectionSeparator(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 6.dp)
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            sectionTitle = "Trending Movies"
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(items = it) { item ->
                                MovieCardPortraitCompact(
                                    movie = item,
                                    onItemClick = { movie ->
                                        navigator.navigate("/details/${movie.id}")
                                    }
                                )
                            }
                        }
                    }
                    //endregion

                    //region Upcoming Movies
                    homeUiState.upcomingMovies?.let {
                        SectionSeparator(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp, top = 12.dp)
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            sectionTitle = "Upcoming Movies"
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                            modifier = Modifier.wrapContentHeight()
                        ) {
                            items(items = it) { item ->
                                MovieCardLandscape(
                                    modifier = Modifier
                                        .width(300.dp)
                                        .height(245.dp),
                                    movie = item,
                                    onClickItem = { movie ->
                                        navigator.navigate("/details/${movie.id}")
                                    }
                                )
                            }
                        }
                    }
                    //endregion

                    //region Popular Movies
                    homeUiState.popularMovies?.let {
                        Column(modifier = Modifier.padding(bottom = 90.dp)) {
                            SectionSeparator(
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 16.dp, top = 12.dp)
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                sectionTitle = "Popular Movies"
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            LazyRow(
                                modifier = Modifier.wrapContentHeight(),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                items(items = it) { item ->
                                    MovieCardPortraitCompact(
                                        movie = item,
                                        onItemClick = { movie ->
                                            navigator.navigate("/details/${movie.id}")
                                        }
                                    )
                                }
                            }
                        }
                    }
                    //endregion
                }
            }
        }
        // endregion
    }
}
