package com.vickbt.composeApp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kmpalette.loader.NetworkLoader
import com.kmpalette.loader.rememberNetworkLoader
import com.kmpalette.rememberDominantColorState
import com.vickbt.composeApp.domain.models.Movie
import com.vickbt.composeApp.ui.components.ratingbar.RatingBar
import com.vickbt.composeApp.ui.components.ratingbar.RatingBarStyle
import com.vickbt.composeApp.ui.components.ratingbar.StepSize
import com.vickbt.composeApp.utils.capitalizeEachWord
import com.vickbt.composeApp.utils.getRating
import com.vickbt.composeApp.utils.getReleaseDate
import com.vickbt.composeApp.utils.loadImage
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.unknown_movie
import io.ktor.http.Url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieCardLandscape(
    modifier: Modifier = Modifier,
    movieId:Int,
    backdropPath: String? = null,
    title:String?=null,
    voteAverage:Double?=null,
    releaseDate:String?=null,
    networkLoader: NetworkLoader = rememberNetworkLoader(),
    onClickItem: (Int) -> Unit
) {
    val dominantColorState = rememberDominantColorState(
        loader = networkLoader,
        defaultColor = Color.DarkGray,
        defaultOnColor = Color.LightGray,
        coroutineContext = Dispatchers.IO
    )

    backdropPath?.loadImage()?.let {
        LaunchedEffect(it) {
            dominantColorState.updateFrom(Url(it))
        }
    }

    Card(
        modifier = modifier.clickable { onClickItem(movieId) },
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Box(modifier = modifier) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                model = backdropPath?.loadImage(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )
            //endregion

            //region Fading Edge
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                dominantColorState.color
                            )
                        )
                    )
                    .align(Alignment.BottomCenter)
            )
            //endregion

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.BottomCenter)
            ) {
                //region Movie Title
                Text(
                    modifier = Modifier,
                    text = title ?: stringResource(Res.string.unknown_movie),
                    maxLines = 2,
                    style = MaterialTheme.typography.headlineLarge,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    color = dominantColorState.onColor
                )
                //endregion

                //region Movie Rating
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingBar(
                        modifier = Modifier,
                        value = voteAverage?.getRating()?.toFloat() ?: 0f,
                        numOfStars = 5,
                        size = 15.dp,
                        stepSize = StepSize.HALF,
                        isIndicator = true,
                        style = RatingBarStyle.Fill()
                    )

                    releaseDate?.let {
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .width(1.dp)
                                .height(13.dp),
                            color = dominantColorState.onColor
                        )

                        Text(
                            modifier = Modifier,
                            text = releaseDate.getReleaseDate().capitalizeEachWord(),
                            maxLines = 1,
                            style = MaterialTheme.typography.bodyMedium,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Start,
                            color = dominantColorState.onColor
                        )
                    }
                }
                //endregion
            }
        }
    }
}
