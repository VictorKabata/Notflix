@file:OptIn(ExperimentalFoundationApi::class)

package com.vickikbt.notflix.ui.activity

import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.vickikbt.notflix.R
import com.vickikbt.notflix.util.LocaleManager
import com.vickikbt.shared.presentation.presenters.SharedMainPresenter
import com.vickikbt.shared.presentation.ui.screens.main.MainScreen
import com.vickikbt.shared.presentation.ui.theme.NotflixTheme
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent
import org.koin.android.ext.android.inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
class MainActivity : PreComposeActivity() {

    private val localeUtil by inject<LocaleManager>()
    private val viewModel by inject<SharedMainPresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
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
