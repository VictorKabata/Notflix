package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.vickikbt.domain.models.Movie
import com.vickikbt.notflix.ui.screens.home.HomeViewModel
import com.vickikbt.notflix.util.loadImage
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ItemPopularMovies(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    movie: Movie,
    onClickItem: (Movie) -> Unit
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    val defaultDominantTextColor = MaterialTheme.colors.onSurface
    val dominantColor = remember { mutableStateOf(defaultDominantColor) }
    val dominantTextColor = remember { mutableStateOf(defaultDominantTextColor) }
    val dominantSubTextColor = remember { mutableStateOf(defaultDominantTextColor) }

    Card(
        modifier = modifier.clickable { onClickItem(movie) },
        elevation = 8.dp,
        shape = RoundedCornerShape(4.dp)
    ) {

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (imageMovieCover, boxFadingEdge, textMovieTitle, ratingStarRanking) = createRefs()


            val painter = rememberImagePainter(
                data = movie.backdropPath?.loadImage(),
                builder = { crossfade(true) }
            )

            //region Movie Cover
            Image(
                modifier = Modifier
                    .width(300.dp)
                    .height(210.dp)
                    .constrainAs(imageMovieCover) {},
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                painter = painter,
                contentDescription = null
            )
            //endregion

            //region Fading Edge
            if (painter.state is ImagePainter.State.Success) {
                LaunchedEffect(key1 = painter) {
                    launch {
                        val imageDrawable = painter.imageLoader.execute(painter.request).drawable
                        viewModel.getImagePalette(imageDrawable!!) {
                            dominantColor.value = Color(it.rgb)
                            dominantTextColor.value = Color(it.titleTextColor)
                            dominantSubTextColor.value = Color(it.bodyTextColor)
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                dominantColor.value
                            )
                        )
                    )
                    .constrainAs(boxFadingEdge) {
                        bottom.linkTo(parent.bottom)
                    }
            )
            //endregion

            /*Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .constrainAs(textSongTitle) {
                        width = Dimension.fillToConstraints
                        start.linkTo(parent.start)
                        bottom.linkTo(textSongArtist.top)
                        end.linkTo(fabPlaySong.start)
                    },
                text = song.title ?: "Unknown song title",
                fontSize = 24.sp,
                maxLines = 1,
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = dominantTextColor.value
            )

            Text(
                modifier = Modifier
                    .padding(bottom = 20.dp, start = 16.dp, end = 8.dp)
                    .constrainAs(textSongArtist) {
                        width = Dimension.fillToConstraints
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(fabPlaySong.start)
                    },
                text = if (song.artistName.isNullOrEmpty() || song.artistName == "<unknown>") "Unknown artist" else song.artistName,
                fontSize = 15.sp,
                maxLines = 1,
                style = MaterialTheme.typography.h5,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = dominantSubTextColor.value
            )

            FloatingActionButton(
                modifier = Modifier
                    .padding(end = 8.dp, bottom = 24.dp)
                    .alpha(0.4f)
                    .constrainAs(fabPlaySong) {
                        end.linkTo(parent.end)
                        top.linkTo(textSongTitle.top)
                        bottom.linkTo(textSongArtist.bottom)
                    },
                backgroundColor = MaterialTheme.colors.surface,
                onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = stringResource(id = R.string.play)
                )
            }
        }*/
        }
    }
}