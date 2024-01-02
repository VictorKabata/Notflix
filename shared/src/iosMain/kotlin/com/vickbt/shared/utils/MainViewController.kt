package com.vickbt.shared.utils

import androidx.compose.ui.window.ComposeUIViewController
import com.vickbt.shared.ui.screens.main.MainScreen
import moe.tlaster.precompose.PreComposeApplication

fun MainViewController() = ComposeUIViewController { MainScreen() }
