package com.vickikbt.notflix.ui.screens.favorites

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.components.AppBars.AppBar
import com.vickikbt.notflix.ui.components.ItemFavoriteMovie
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
    Scaffold(topBar = { AppBar(stringResource(id = R.string.title_favorites)) }) {
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
    }
}
