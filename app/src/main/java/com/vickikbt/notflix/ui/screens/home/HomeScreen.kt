package com.vickikbt.notflix.ui.screens.home

<<<<<<< Updated upstream
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
=======
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
>>>>>>> Stashed changes
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = { crash() },
            modifier = Modifier.align(Alignment.Center),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(text = "Crash", color = Color.Black)
        }
    }
}

<<<<<<< Updated upstream
fun crash() {
    throw RuntimeException("Test Crash")
}
=======
    val scrollState = rememberScrollState()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface){
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)) {

        }

    }

}

@Composable
fun UpcomingMovies(navController: NavController){

}
>>>>>>> Stashed changes
