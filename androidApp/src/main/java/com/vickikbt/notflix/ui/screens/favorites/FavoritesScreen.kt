package com.vickikbt.notflix.ui.screens.favorites

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel

@ExperimentalFoundationApi
@Composable
fun FavoritesScreen(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel = getViewModel()
) {
    val scrollState = rememberScrollState()
    val favouriteMovies = favoritesViewModel.favouriteMovies.observeAsState().value
    Log.d("FavoritesScreen", favouriteMovies?.size.toString())
    /*Scaffold(topBar = { AppBar(stringResource(id = R.string.title_favorites)) }) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
            if (favouriteMovies != null) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .scrollable(scrollState, orientation = Orientation.Vertical)
                ) {
                    items(favouriteMovies) { movie ->
                        ItemFavoriteMovie(movie = movie)
                    }
                }
            }
        }
    }*/
}
