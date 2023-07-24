package com.vickikbt.shared.utils

import com.vickikbt.shared.presentation.ui.screens.main.MainScreen
import moe.tlaster.precompose.PreComposeApplication

fun MainViewController() = PreComposeApplication(title = "Notflix") { MainScreen() }
