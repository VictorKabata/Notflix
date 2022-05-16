package ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utils.koil

@Composable
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        val imageAsset = koil(url = "https://image.tmdb.org/t/p/original/AdyJH8kDm8xT8IKTlgpEC15ny4u.jpg")
        
        imageAsset?.let {
            Image(
                modifier = Modifier.fillMaxSize().align(Alignment.Center),
                bitmap = it,
                contentDescription = "Image",
                contentScale = ContentScale.Crop
            )
        }

        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 60.dp).align(Alignment.BottomStart),
            text = "Doctor Strange in the Multiverse of Madness",
            fontSize = 48.sp,
            maxLines = 2,
            style = MaterialTheme.typography.h6,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            color = Color.White
        )
    }
}
