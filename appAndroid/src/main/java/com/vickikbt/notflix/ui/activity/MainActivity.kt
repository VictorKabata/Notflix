@file:OptIn(ExperimentalFoundationApi::class)

package com.vickikbt.notflix.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.navigation.Navigation
import com.vickikbt.notflix.util.LocaleManager
import com.vickikbt.shared.presentation.presenters.SharedMainPresenter
import com.vickikbt.shared.presentation.ui.components.BottomNavBar
import com.vickikbt.shared.presentation.ui.navigation.NavigationItem
import com.vickikbt.shared.presentation.ui.theme.NotflixTheme
import org.koin.android.ext.android.inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    private val localeUtil by inject<LocaleManager>()
    private val viewModel by inject<SharedMainPresenter>()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()

            val currentTheme = viewModel.appTheme.collectAsState().value
            val isDarkTheme =
                when (stringArrayResource(id = R.array.theme_entries)[currentTheme ?: 0]) {
                    "light_theme" -> false
                    "dark_theme" -> true
                    else -> isSystemInDarkTheme()
                }

            val currentLanguage = viewModel.appLanguage.collectAsState().value
            val languageEntry =
                stringArrayResource(id = R.array.language_entries)[currentLanguage ?: 0]
            localeUtil.setLocale(
                context = LocalContext.current,
                language = languageEntry
            )

            NotflixTheme(darkTheme = isDarkTheme) {
                Surface(color = MaterialTheme.colors.surface) {
                    MainScreen()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MainScreen() {
    val navController = rememberAnimatedNavController()

    val topLevelDestinations = listOf(
        NavigationItem.Home,
        NavigationItem.Favorites,
        NavigationItem.Settings
    )

    val isTopLevelDestination =
        navController.currentBackStackEntryAsState().value?.destination?.route in topLevelDestinations.map { it.route }


    Scaffold(
        bottomBar = { if (isTopLevelDestination) BottomNavBar(bottomNavItems = topLevelDestinations) }
    ) {
        Navigation(navController = navController)
    }
}
