package com.vickbt.composeApp.ui.components.ratingbar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun RatingStar(
    fraction: Float,
    modifier: Modifier = Modifier,
    style: RatingBarStyle,
    painterEmpty: Painter?,
    painterFilled: Painter?
) {
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    Box(modifier = modifier) {
        FilledStar(
            fraction,
            style,
            isRtl,
            painterFilled
        )
        EmptyStar(fraction, style, isRtl, painterEmpty)
    }
}

@Composable
private fun FilledStar(
    fraction: Float,
    style: RatingBarStyle,
    isRtl: Boolean,
    painterFilled: Painter?
) = Canvas(
    modifier = Modifier
        .fillMaxSize()
        .clip(
            if (isRtl) {
                rtlFilledStarFractionalShape(fraction = fraction)
            } else {
                FractionalRectangleShape(0f, fraction)
            }
        )
) {
    if (painterFilled != null) {
        with(painterFilled) {
            draw(
                size = Size(size.height, size.height),
            )
        }
    } else {
        val path = Path().addStar(size)

        drawPath(path, color = style.activeColor, style = Fill) // Filled Star
        drawPath(
            path,
            color = style.activeColor,
            style = Stroke(width = if (style is RatingBarStyle.Stroke) style.width else 1f)
        ) // Border
    }
}

@Composable
private fun EmptyStar(
    fraction: Float,
    style: RatingBarStyle,
    isRtl: Boolean,
    painterEmpty: Painter?
) =
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .clip(
                if (isRtl) {
                    rtlEmptyStarFractionalShape(fraction = fraction)
                } else {
                    FractionalRectangleShape(fraction, 1f)
                }
            )
    ) {
        if (painterEmpty != null) {
            with(painterEmpty) {
                draw(
                    size = Size(size.height, size.height),
                )
            }
        } else {
            val path = Path().addStar(size)
            if (style is RatingBarStyle.Fill) {
                drawPath(
                path,
                color = style.inActiveColor,
                style = Fill
            ) // Border
            } else if (style is RatingBarStyle.Stroke) {
                drawPath(
                path,
                    color = style.strokeColor,
                    style = Stroke(width = style.width)
            ) // Border
            }
        }
    }

fun rtlEmptyStarFractionalShape(fraction: Float): FractionalRectangleShape {
    return if (fraction == 1f || fraction == 0f) {
        FractionalRectangleShape(fraction, 1f)
    } else {
        FractionalRectangleShape(0f, 1f - fraction)
    }
}

fun rtlFilledStarFractionalShape(fraction: Float): FractionalRectangleShape {
    return if (fraction == 0f || fraction == 1f) {
        FractionalRectangleShape(0f, fraction)
    } else {
        FractionalRectangleShape(1f - fraction, 1f)
    }
}
