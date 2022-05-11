package com.vickikbt.notflix.ui.activity

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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.components.BottomNavBar
import com.vickikbt.notflix.ui.navigation.Navigation
import com.vickikbt.notflix.ui.navigation.NavigationItem
import com.vickikbt.notflix.ui.screens.settings.SettingsViewModel
import com.vickikbt.notflix.ui.theme.NotflixTheme
import com.vickikbt.notflix.util.ChangeSystemBarColorOnNetChange
import com.vickikbt.notflix.util.LocaleManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    private val localeUtil by inject<LocaleManager>()
    private val viewModel by inject<MainViewModel>()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.registerCallback()
        setContent {
            val settingsViewModel: SettingsViewModel = getViewModel()
            val systemUiController = rememberSystemUiController()

            val currentTheme = settingsViewModel.selectedTheme.observeAsState().value!!
            val useDarkTheme =
                when (stringArrayResource(id = R.array.theme_entries)[currentTheme]) {
                    "light_theme" -> false
                    "dark_theme" -> true
                    else -> isSystemInDarkTheme()
                }

            val currentLanguage = settingsViewModel.selectedLanguage.observeAsState().value!!
            val languageEntry = stringArrayResource(id = R.array.language_entries)[currentLanguage]
            localeUtil.setLocale(
                context = LocalContext.current,
                language = languageEntry
            )

            ChangeSystemBarColorOnNetChange(
                key = viewModel.isNetworkOn.collectAsState().value,
                systemUiController = systemUiController,
            )

            NotflixTheme(darkTheme = useDarkTheme, systemUiController = systemUiController) {
                Surface(color = MaterialTheme.colors.surface) {
                    MainScreen()
                }
            }
        }
    }
}

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

    val backStackEntryState = navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            if (isTopLevelDestination) {
                BottomNavBar(
                    navController = navController,
                    backStackEntryState = backStackEntryState,
                    bottomNavItems = topLevelDestinations
                )
            }
        }
    ) {
        Navigation(navController = navController)
    }
}
