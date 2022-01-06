package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.vickikbt.notflix.ui.theme.TextSecondary

@Composable
fun TrailerSection(modifier: Modifier) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        val (textTrailer, playerCard) = createRefs()

        Text(
            text = "Trailer",
            style = MaterialTheme.typography.h6,
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(textTrailer) {
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 10.dp)
            }
        )
        Card(
            modifier = Modifier
                .constrainAs(playerCard) {
                    start.linkTo(parent.start, margin = 10.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    top.linkTo(textTrailer.bottom, margin = 5.dp)
                    width = Dimension.fillToConstraints
                }
                .height(200.dp),
            shape = RoundedCornerShape(10.dp),
            backgroundColor = TextSecondary
        ) {
//        
        }
    }
}
