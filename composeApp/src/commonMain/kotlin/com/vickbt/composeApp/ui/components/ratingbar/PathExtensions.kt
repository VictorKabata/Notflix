package com.vickbt.composeApp.ui.components.ratingbar

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun Path.addStar(
    size: Size,
    spikes: Int = 5,
    outerRadiusFraction: Float = 0.5f,
    innerRadiusFraction: Float = 0.2f
): Path {
    val outerRadius = size.minDimension * outerRadiusFraction
    val innerRadius = size.minDimension * innerRadiusFraction

    val centerX = size.width / 2
    val centerY = size.height / 2

    var totalAngle = PI / 2 // Since we start at the top center, the initial angle will be 90°
    val degreesPerSection = (2 * PI) / spikes

    moveTo(centerX, 0f) // Starts at the top center of the bounds

    var x: Double
    var y: Double

    for (i in 1..spikes) {
        // Line going inwards from outerCircle to innerCircle
        totalAngle += degreesPerSection / 2
        x = centerX + cos(totalAngle) * innerRadius
        y = centerY - sin(totalAngle) * innerRadius
        lineTo(x.toFloat(), y.toFloat())

        // Line going outwards from innerCircle to outerCircle
        totalAngle += degreesPerSection / 2
        x = centerX + cos(totalAngle) * outerRadius
        y = centerY - sin(totalAngle) * outerRadius
        lineTo(x.toFloat(), y.toFloat())
    }

    // Path should be closed to ensure it's not an open shape
    close()

    return this
}
