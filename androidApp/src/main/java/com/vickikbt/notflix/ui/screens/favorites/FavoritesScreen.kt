package com.vickikbt.notflix.ui.screens.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.components.ItemFavoriteMovie
import com.vickikbt.notflix.ui.components.app_bars.AppBar
import com.vickikbt.shared.presentation.presenters.SharedFavouritesPresenter
import org.koin.androidx.compose.get

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalFoundationApi
@Composable
fun FavoritesScreen(
    navController: NavController,
    favoritesViewModel: SharedFavouritesPresenter = get()
) {
    val favouriteMovies = favoritesViewModel.favouriteMovies.collectAsState().value

    Scaffold(topBar = { AppBar(stringResource(id = R.string.title_favorites)) }) {

        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {

            favouriteMovies?.let {
                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    items(favouriteMovies) { movie ->
                        ItemFavoriteMovie(movie = movie) {
                            navController.navigate("details/${it.id}/${0}")
                        }
                    }

                }
            }
        }

    }
}
