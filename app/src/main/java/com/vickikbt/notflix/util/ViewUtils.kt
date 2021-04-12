package com.vickikbt.notflix.util

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.log(message: String) {
    Log.e("VickiKbt", message)
}