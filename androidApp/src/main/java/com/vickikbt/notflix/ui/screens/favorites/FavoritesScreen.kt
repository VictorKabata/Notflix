package com.vickikbt.notflix.ui.screens.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.components.ItemFavoriteMovie
import com.vickikbt.notflix.ui.components.app_bars.AppBar
import com.vickikbt.shared.presentation.presenters.SharedFavouritesPresenter
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.get

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalFoundationApi
@Composable
fun FavoritesScreen(
    navController: NavController,
    favoritesViewModel: SharedFavouritesPresenter = get()
) {
    val scrollState = rememberScrollState()
    val favouriteMovies = favoritesViewModel.favouriteMovies.collectAsState().value

    Napier.e("Favourite Movie: $favouriteMovies")

    Scaffold(topBar = { AppBar(stringResource(id = R.string.title_favorites)) }) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
            if (favouriteMovies != null) {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
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
