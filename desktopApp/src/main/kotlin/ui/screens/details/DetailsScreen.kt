package ui.screens.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.vickikbt.shared.presentation.presenters.SharedDetailsPresenter
import io.github.aakira.napier.Napier
import koin
import ui.navigation.NavController

@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: SharedDetailsPresenter = koin.get(),
    movieId: Int
) {
    Text("Details screen")

    /*LaunchedEffect(key1 = viewModel) {
        viewModel.getIsMovieFavorite(movieId)
        viewModel.getMovieDetails(movieId)
        viewModel.fetchSimilarMovies(movieId)
        viewModel.getMovieCast(movieId)
    }

    val movieDetails = viewModel.movieDetails.collectAsState().value
    val movieCast = viewModel.movieCast.collectAsState().value
    val similarMovies = viewModel.similarMovies.collectAsState().value
    val movieVideo = viewModel.movieVideo.collectAsState().value
    val isMovieFavorite = viewModel.movieIsFavorite.collectAsState().value

    val scrollState = rememberScrollState()

    println("MovieID: $movieId")
    println("Movie details: $movieDetails")

    Column(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        //region Movie Ratings
        val voteAverage = movieDetails?.voteAverage
        MovieRatingSection(
            popularity = voteAverage?.getPopularity(),
            voteAverage = voteAverage?.getRating()
        )
        //endregion

        //region Movie Overview
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Overview",
            style = MaterialTheme.typography.h6,
            fontSize = 20.sp,
            color = MaterialTheme.colors.onSurface,
        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = movieDetails?.overview ?: "",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface,
            fontSize = 15.sp,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
        )
        //endregion

        //region Movie Cast
        movieCast?.actor?.let {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Cast",
                style = MaterialTheme.typography.h6,
                fontSize = 20.sp
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items = it) { item ->
                    ItemMovieCast(actor = item)
                }
            }
        }
        //endregion

        //region Similar Movies
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Similar Movies",
            style = MaterialTheme.typography.h6,
            fontSize = 20.sp,
            color = MaterialTheme.colors.onSurface
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            similarMovies?.let {
                items(items = it) { movie ->
                    ItemSimilarMovies(movie = movie)
                }
            }
        }
        //endregion
    }*/
}
