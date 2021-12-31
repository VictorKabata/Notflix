package com.vickikbt.notflix.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.vickikbt.notflix.ui.navigation.Navigation
import com.vickikbt.notflix.ui.navigation.NavigationItem

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }

    @ExperimentalAnimationApi
    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    @Composable
    fun MainScreen(){
        val navController = rememberAnimatedNavController()

        val topLevelDestinations = listOf(
            NavigationItem.Home,
            NavigationItem.Favorites,
            NavigationItem.Settings
        )

        val isTopLevelDestination =
            navController.currentBackStackEntryAsState().value?.destination?.route in topLevelDestinations.map { it.route }

        val backStackEntryState = navController.currentBackStackEntryAsState()
        
        Scaffold(
            bottomBar = {}
        ) {
            Navigation(navController = navController)
        }

    }

}