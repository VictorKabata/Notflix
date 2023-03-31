package ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.vickikbt.shared.utils.getPopularity
import com.vickikbt.shared.utils.getRating
import koin
import ui.components.ItemMovieCast
import ui.components.ItemSimilarMovies
import ui.components.MovieRatingSection
import ui.navigation.NavController
import utils.AsyncImage
import utils.loadImage
import utils.loadImageBitmap

@Composable
fun DetailsComposableScreen(
    navController: NavController,
    viewModel: DetailsScreenModel = koin.get(),
    movieId: Int
) {
    LaunchedEffect(key1 = viewModel) {
        // viewModel.getIsMovieFavorite(movieId)
        viewModel.getMovieDetails(movieId)
        viewModel.getMovieCast(movieId)
        viewModel.fetchSimilarMovies(movieId)
    }

    val movieDetails = viewModel.movieDetailsState.collectAsState().value.movieDetails
    val movieCast = viewModel.movieDetailsState.collectAsState().value
    val similarMovies = viewModel.movieDetailsState.collectAsState().value.similarMovies
    // val isMovieFavorite = viewModel.movieIsFavorite.collectAsState().value

    val scrollState = rememberScrollState()

    println("MovieID: $movieId")
    println("Movie details: $movieDetails")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        // Movie Poster region

        Box(modifier = Modifier.fillMaxWidth().height(600.dp)) {
            movieDetails?.backdropPath?.let {
                val imgUrl = it.loadImage()

                println("Image URL: $imgUrl")

                AsyncImage(
                    modifier = Modifier.fillMaxSize().align(Alignment.Center),
                    load = { loadImageBitmap(imgUrl) },
                    painterFor = { remember { BitmapPainter(it) } },
                    contentDescription = "Image poster",
                    contentScale = ContentScale.Crop
                )
            }

            Icon(
                modifier = Modifier.size(24.dp).padding(24.dp).align(Alignment.TopStart),
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colors.onSurface
            )
        }
        // endregion

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
        movieCast.movieCast?.let {
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
    }
}
