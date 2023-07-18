package com.vickikbt.shared.presentation.ui.components

import androidx.compose.runtime.Composable
import com.vickikbt.shared.domain.models.Movie

@Composable
fun ItemTrendingMovies(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    /*Column(
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Card(
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight()
                .clickable { onItemClick(movie) },
            elevation = 8.dp,
            shape = RoundedCornerShape(4.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .sizeIn(minHeight = 30.dp),
                painter = rememberImagePainter(
                    data = movie.posterPath?.loadImage(),
                    builder = { crossfade(true) }
                ),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(id = R.string.trending_movies)
            )
        }

        Text(
            modifier = Modifier
                .width(145.dp)
                .placeholder(
                    visible = isLoading,
                    color = Color.Gray,
                    shape = RoundedCornerShape(0.dp)
                ),
            text = movie.title ?: "Unknown movie",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onSurface,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )
    }*/
}

@Composable
private fun Preview() {
    ItemTrendingMovies(movie = Movie(title = "Cocaine Bear"), onItemClick = {})
}
