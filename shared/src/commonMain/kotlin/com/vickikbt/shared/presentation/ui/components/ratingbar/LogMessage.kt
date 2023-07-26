package com.vickikbt.shared.presentation.ui.components.ratingbar

object LogMessage {

    private const val logVisible = false

    internal fun v(msg: String) {
        if (logVisible) println("Compose-Ratingbar :$msg")
    }

    internal fun e(msg: String) {
        if (logVisible) println("Compose-Ratingbar: $msg")
    }

}
