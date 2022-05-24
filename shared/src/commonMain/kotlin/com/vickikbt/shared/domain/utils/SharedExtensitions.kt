package com.vickikbt.shared.domain.utils

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } }
