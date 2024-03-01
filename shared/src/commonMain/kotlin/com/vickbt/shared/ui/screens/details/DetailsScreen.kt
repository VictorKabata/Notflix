package com.vickbt.shared.ui.screens.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickbt.shared.domain.utils.Enums.Categories
import com.vickbt.shared.ui.components.ActionButton
import com.vickbt.shared.ui.components.EpisodeCardPager
import com.vickbt.shared.ui.components.ItemMovieCast
import com.vickbt.shared.ui.components.MovieCardPortrait
import com.vickbt.shared.ui.components.SeasonsDropDown
import com.vickbt.shared.ui.components.appbars.DetailsAppBar
import com.vickbt.shared.ui.components.collapsingToolbar.CollapsingToolbarScaffold
import com.vickbt.shared.ui.components.collapsingToolbar.ScrollStrategy
import com.vickbt.shared.ui.components.collapsingToolbar.rememberCollapsingToolbarScaffoldState
import com.vickbt.shared.utils.WindowSize
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.koinInject

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsScreen(
    navigator: Navigator,
    windowSize: WindowSize = WindowSize.COMPACT,
    viewModel: DetailsViewModel = koinInject(),
    movieId: Int,
    category: Categories
) {
    LaunchedEffect(key1 = Unit) {
        if (category == Categories.MOVIE) {
            viewModel.getMovieDetails(movieId = movieId)
        } else {
            viewModel.getShowDetails(showId = movieId)
        }
    }

    val movieDetailsState = viewModel.movieDetailsState.collectAsState().value

    val scrollState = rememberScrollState()
    val collapsingScrollState = rememberCollapsingToolbarScaffoldState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (movieDetailsState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (!movieDetailsState.error.isNullOrEmpty()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Error:\n${movieDetailsState.error}",
                textAlign = TextAlign.Center
            )
        } else {
            CollapsingToolbarScaffold(
                modifier = Modifier.fillMaxSize(),
                state = collapsingScrollState,
                scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
                toolbar = {
                    DetailsAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        collapsingScrollState = collapsingScrollState,
                        movieDetailsState = movieDetailsState,
                        onNavigationIconClick = { navigator.goBack() },
                        onShareIconClick = {},
                        onFavoriteIconClick = { movieDetails, isFavorite ->
                            /*if (isFavorite == true) {
                                viewModel.saveFavoriteMovie(movieDetails = movieDetails)
                            } else {
                                viewModel.deleteFavoriteMovie(movieId = movieDetails.id)
                            }*/
                        }
                    )
                }
            ) {
                Column(
                    modifier = Modifier.padding(bottom = 20.dp).verticalScroll(state = scrollState),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    //region Play Buttons
                    ActionButton(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        buttonText = "Watch Now",
                        buttonIcon = Icons.Rounded.PlayArrow
                    ) {
                        // ToDo: Play video
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(
                            6.dp, Alignment.CenterHorizontally
                        )
                    ) {
                        movieDetailsState.movieDetails?.youtubeTrailerId?.let {
                            ActionButton(
                                modifier = Modifier.weight(1f),
                                buttonText = "Watch Trailer",
                                buttonIcon = Icons.Rounded.Movie,
                                buttonColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.onSecondary
                            ) {
                                // ToDo: Open youtube app to play trailer
                            }
                        }

                        ActionButton(
                            modifier = Modifier.weight(1f),
                            buttonText = "Download",
                            buttonIcon = Icons.Rounded.Download,
                            buttonColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        ) {
                            // ToDo: Download movie/tv show
                        }
                    }
                    //endregion

                    //region Movie Overview
                    if (!movieDetailsState.movieDetails?.overview.isNullOrEmpty()) {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Overview",
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            text = movieDetailsState.movieDetails?.overview ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    //endregion

                    //region TV Show Episodes
                    if (category == Categories.TV_SHOW) {
                        Row(
                            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "Episodes",
                                style = MaterialTheme.typography.titleMedium,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                            )

                            SeasonsDropDown(
                                modifier = Modifier,
                                content = movieDetailsState.movieDetails?.seasons ?: listOf()
                            ) { season ->
                                viewModel.getSeasonEpisodes(seasonId = season.id)
                            }
                        }

                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = 16.dp)
                        ) {
                            items(items = movieDetailsState.episodes ?: listOf()) {
                                EpisodeCardPager(
                                    modifier = Modifier.width(180.dp).height(120.dp),
                                    episode = it
                                ) { episode ->
                                    // ToDo: Open player to play episode
                                    // navigator.navigate("/details/${movie?.id}")
                                }
                            }
                        }
                    }
                    //endregion

                    //region Movie Cast
                    if (!movieDetailsState.movieDetails?.cast.isNullOrEmpty()) {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Cast",
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 20.sp
                        )

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(
                                items = movieDetailsState.movieDetails?.cast ?: emptyList()
                            ) { item ->
                                ItemMovieCast(modifier = Modifier, people = item)
                            }
                        }
                    }
                    //endregion

                    //region Similar Movies
                    if (!movieDetailsState.movieDetails?.recommendations.isNullOrEmpty()) {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Recommended Movies",
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(
                                items = movieDetailsState.movieDetails?.recommendations
                                    ?: emptyList()
                            ) { movie ->
                                MovieCardPortrait(movie = movie, onItemClick = {})
                            }
                        }
                    }
                    //endregion
                }
            }
        }
    }
}
