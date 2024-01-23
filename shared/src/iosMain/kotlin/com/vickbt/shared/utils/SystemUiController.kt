@file:OptIn(ExperimentalForeignApi::class)

package com.vickbt.shared.utils

import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.zeroValue
import platform.CoreGraphics.CGColorRef
import platform.CoreGraphics.CGRect
import platform.UIKit.UIApplication
import platform.UIKit.UIColor
import platform.UIKit.UINavigationBar
import platform.UIKit.UIView
import platform.UIKit.UIWindow
import platform.UIKit.statusBarManager
import androidx.compose.runtime.Composable as Composable1

@Composable1
actual fun systemBarsUiController(statusBarColor: Color, navBarColor: Color) {
    val statusBar = statusBarView()
    SideEffect {
        statusBar.backgroundColor = statusBarColor.toUIColor()
        UINavigationBar.appearance().backgroundColor = navBarColor.toUIColor()
    }
}

@Composable1
private fun statusBarView() = remember {
    val keyWindow: UIWindow? =
        UIApplication.sharedApplication.windows.firstOrNull { (it as? UIWindow)?.isKeyWindow() == true } as? UIWindow
    val tag = 3848245L
    // https://stackoverflow.com/questions/56651245/how-to-change-the-status-bar-background-color-and-text-color-on-ios-13

    keyWindow?.viewWithTag(tag)?.let {
        it
    } ?: run {
        val height =
            keyWindow?.windowScene?.statusBarManager?.statusBarFrame ?: zeroValue<CGRect>()
        val statusBarView = UIView(frame = height)
        statusBarView.tag = tag
        statusBarView.layer.zPosition = 999999.0
        keyWindow?.addSubview(statusBarView)
        statusBarView
    }
}

private fun Color.toUIColor(): UIColor = UIColor(
    red = this.red.toDouble(),
    green = this.green.toDouble(),
    blue = this.blue.toDouble(),
    alpha = this.alpha.toDouble()
)
