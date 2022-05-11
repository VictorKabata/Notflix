package com.vickikbt.notflix.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.navigation.NavigationItem
import com.vickikbt.notflix.ui.screens.home.HomeViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

@Composable
fun NotflixSplashScreen(
    homeViewModel: HomeViewModel = getViewModel(),
    navController: NavController
) {

    LaunchedEffect(key1 = true) {
        delay(2000L)
        navController.navigate(NavigationItem.Home.route)
    }

    Box(Modifier.fillMaxSize().background(color = MaterialTheme.colors.surface), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "logo", modifier = Modifier.height(50.dp))
    }
}
