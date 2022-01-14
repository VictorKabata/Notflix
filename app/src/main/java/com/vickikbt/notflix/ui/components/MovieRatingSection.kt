package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.theme.Gray
import com.vickikbt.notflix.ui.theme.Surface
import com.vickikbt.notflix.ui.theme.TextSecondary

@Composable
fun MovieRatingSection(popularity: String?, voteAverage: Float?, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .placeholder(
                visible = popularity.isNullOrEmpty(),
                color = Gray,
                highlight = PlaceholderHighlight.shimmer(highlightColor = TextSecondary)
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ConstraintLayout(Modifier.wrapContentHeight().fillMaxWidth()) {
            val (image, popularityRef, popularityText, divider, rating) = createRefs()
            Text(
                text = "$popularity",
                style = MaterialTheme.typography.h6,
                fontSize = 42.sp,
                modifier = Modifier.constrainAs(popularityRef) {
                    top.linkTo(parent.top)
                    start.linkTo(popularityText.start)
                    end.linkTo(popularityText.end)
                }
            )

            Text(
                text = "Popularity", style = MaterialTheme.typography.h6, fontSize = 18.sp,
                modifier = Modifier.constrainAs(popularityText) {
                    top.linkTo(popularityRef.bottom,)
                    end.linkTo(divider.start, margin = 15.dp)
                }
            )

            Divider(
                thickness = 2.dp,
                modifier = modifier
                    .fillMaxHeight(0.7f)
                    .width(2.dp)
                    .constrainAs(divider) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                        height = Dimension.fillToConstraints
                    },
                color = MaterialTheme.colors.onSurface
            )

            Image(
                painter = painterResource(id = R.drawable.ic_rating_star),
                contentDescription = "rating star",
                modifier = modifier.constrainAs(image) {
                    bottom.linkTo(popularityRef.bottom)
                    start.linkTo(rating.start)
                    end.linkTo(rating.end)
                }
            )

            Text(
                text = "$voteAverage/5.0",
                style = MaterialTheme.typography.h6,
                fontSize = 20.sp,
                modifier = Modifier.constrainAs(rating) {
                    start.linkTo(divider.end, margin = 15.dp)
                    top.linkTo(popularityText.top)
                }
            )
        }
    }
}
