package com.vickikbt.shared.utils

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.window.ComposeUIViewController
import com.vickikbt.shared.presentation.ui.screens.home.HomeScreen

@OptIn(ExperimentalMaterialApi::class)
fun MainViewController() = ComposeUIViewController { HomeScreen() }
