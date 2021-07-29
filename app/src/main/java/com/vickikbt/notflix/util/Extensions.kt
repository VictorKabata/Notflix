package com.vickikbt.notflix.util

/**
 * Converts bytes to megabytes
 */
fun Long.toMegabytes(): Int {
    return (this/1024/1024).toInt()
}