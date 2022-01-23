package com.vickikbt.notflix.ui.components.AppBars

import android.widget.Toast
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vickikbt.notflix.R

@Composable
fun DetailsAppBar(modifier: Modifier = Modifier, scrollOffset: Float) {
    val context = LocalContext.current

    TopAppBar(
        modifier = modifier,
        title = { },
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(context, "Clicked navigation icon", Toast.LENGTH_LONG).show()
            }) {
                Icon(Icons.Rounded.ArrowBack, "")
            }
        },
        actions = {
            IconButton(onClick = {
                Toast.makeText(context, "Clicked share icon", Toast.LENGTH_LONG).show()
            }) {
                Icon(Icons.Rounded.Share, "")
            }
            IconButton(onClick = {
                Toast.makeText(context, "Clicked favorites icon", Toast.LENGTH_LONG).show()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favourite),
                    stringResource(id = R.string.title_favorites)
                )
            }
        },
        contentColor = Color.White,
        backgroundColor = Color.Black.copy(alpha = 1f - scrollOffset),
        elevation = 0.dp
    )
}