package com.vickikbt.notflix.ui.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.vickikbt.notflix.R

sealed class MenuAction(@StringRes val label: Int, @DrawableRes val icon: Int) {
    object Favorite : MenuAction(R.string.title_favorites, R.drawable.ic_favourite)
    object Share : MenuAction(R.string.share, R.drawable.ic_github)
}
