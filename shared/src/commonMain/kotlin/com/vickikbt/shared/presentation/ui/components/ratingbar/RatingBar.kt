package com.vickikbt.shared.presentation.ui.components.ratingbar

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

sealed interface StepSize {
    object ONE : StepSize
    object HALF : StepSize
}

sealed class RatingBarStyle(open val activeColor: Color) {
    companion object {
        val Default = Stroke()
    }

    open class Fill(
        override val activeColor: Color = Color(0xFFFFCA00),
        val inActiveColor: Color = Color(0x66FFCA00),
    ) : RatingBarStyle(activeColor)

    /**
     * @param width width for each star
     * @param color A border [Color] shown on inactive star.
     */
    class Stroke(
        val width: Float = 1f,
        override val activeColor: Color = Color(0xFFFFCA00),
        val strokeColor: Color = Color(0xFF888888)
    ) : RatingBarStyle(activeColor)
}

//For ui testing
val StarRatingKey = SemanticsPropertyKey<Float>("StarRating")
var SemanticsPropertyReceiver.starRating by StarRatingKey


/**
 * @param value is current selected rating count
 * @param numOfStars count of stars to be shown.
 * @param size size for each star
 * @param spaceBetween padding between each star.
 * @param isIndicator isIndicator Whether this rating bar is only an indicator or the value is changeable on user interaction.
 * @param stepSize Can be [StepSize.ONE] or [StepSize.HALF]
 * @param hideInactiveStars Whether the inactive stars should be hidden.
 * @param style the different style applied to the Rating Bar.
 * @param onRatingChanged A function to be called when the click or drag is released and rating value is passed
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun RatingBar(
    value: Float,
    modifier: Modifier = Modifier,
    numOfStars: Int = 5,
    size: Dp = 32.dp,
    spaceBetween: Dp = 6.dp,
    isIndicator: Boolean = false,
    stepSize: StepSize = StepSize.ONE,
    hideInactiveStars: Boolean = false,
    style: RatingBarStyle = RatingBarStyle.Default,
    painterEmpty: Painter? = null,
    painterFilled: Painter? = null,
    onValueChange: (Float) -> Unit,
    onRatingChanged: (Float) -> Unit
) {
    var rowSize by remember { mutableStateOf(Size.Zero) }
    var lastDraggedValue by remember { mutableStateOf(0f) }
    val direction = LocalLayoutDirection.current
    val density = LocalDensity.current


    val paddingInPx = remember {
        with(density) { spaceBetween.toPx() }
    }
    val starSizeInPx = remember {
        with(density) { size.toPx() }
    }

    Row(modifier = modifier
        .onSizeChanged { rowSize = it.toSize() }
        .pointerInput(
            Unit
        ) {
            //handling dragging events
            detectHorizontalDragGestures(
                onDragEnd = {
                    if (isIndicator || hideInactiveStars)
                        return@detectHorizontalDragGestures
                    onRatingChanged(lastDraggedValue)
                },
                onDragCancel = {

                },
                onDragStart = {

                },
                onHorizontalDrag = { change, _ ->
                    if (isIndicator || hideInactiveStars)
                        return@detectHorizontalDragGestures
                    change.consume()
                    val dragX = change.position.x.coerceIn(-1f, rowSize.width)
                    var calculatedStars =
                        RatingBarUtils.calculateStars(
                            dragX,
                            paddingInPx,
                            numOfStars, stepSize, starSizeInPx
                        )

                    if (direction == LayoutDirection.Rtl)
                        calculatedStars = numOfStars - calculatedStars
                    onValueChange(calculatedStars)
                    lastDraggedValue = calculatedStars
                }
            )
        }) {
        ComposeStars(
            value,
            numOfStars,
            size,
            spaceBetween,
            hideInactiveStars,
            style = style,
            painterEmpty,
            painterFilled
        )
    }
}

@Composable
fun RatingBar(
    value: Float,
    modifier: Modifier = Modifier,
    numOfStars: Int = 5,
    size: Dp = 32.dp,
    spaceBetween: Dp = 6.dp,
    isIndicator: Boolean = false,
    stepSize: StepSize = StepSize.ONE,
    hideInactiveStars: Boolean = false,
    style: RatingBarStyle,
    onValueChange: (Float) -> Unit,
    onRatingChanged: (Float) -> Unit
) {
    RatingBar(
        value = value,
        modifier = modifier,
        numOfStars = numOfStars,
        size = size,
        spaceBetween = spaceBetween,
        isIndicator = isIndicator,
        stepSize = stepSize,
        hideInactiveStars = hideInactiveStars,
        style = style,
        painterEmpty = null,
        painterFilled = null,
        onValueChange = onValueChange,
        onRatingChanged = onRatingChanged
    )
}

@Composable
fun RatingBar(
    value: Float,
    modifier: Modifier = Modifier,
    numOfStars: Int = 5,
    size: Dp = 32.dp,
    spaceBetween: Dp = 6.dp,
    isIndicator: Boolean = false,
    stepSize: StepSize = StepSize.ONE,
    hideInactiveStars: Boolean = false,
    painterEmpty: Painter,
    painterFilled: Painter,
    onValueChange: (Float) -> Unit,
    onRatingChanged: (Float) -> Unit
) {
    RatingBar(
        value = value,
        modifier = modifier,
        numOfStars = numOfStars,
        size = size,
        spaceBetween = spaceBetween,
        isIndicator = isIndicator,
        stepSize = stepSize,
        hideInactiveStars = hideInactiveStars,
        style = RatingBarStyle.Default,
        painterEmpty = painterEmpty,
        painterFilled = painterFilled,
        onValueChange = onValueChange,
        onRatingChanged = onRatingChanged
    )
}

@Composable
fun ComposeStars(
    value: Float,
    numOfStars: Int,
    size: Dp,
    spaceBetween: Dp,
    hideInactiveStars: Boolean,
    style: RatingBarStyle,
    painterEmpty: Painter?,
    painterFilled: Painter?
) {

    val ratingPerStar = 1f
    var remainingRating = value

    Row(modifier = Modifier
        .semantics { starRating = value }) {
        for (i in 1..numOfStars) {
            val starRating = when {
                remainingRating == 0f -> {
                    0f
                }

                remainingRating >= ratingPerStar -> {
                    remainingRating -= ratingPerStar
                    1f
                }

                else -> {
                    val fraction = remainingRating / ratingPerStar
                    remainingRating = 0f
                    fraction
                }
            }
            if (hideInactiveStars && starRating == 0.0f)
                break

            RatingStar(
                fraction = starRating,
                style = style,
                modifier = Modifier
                    .padding(
                        start = if (i > 1) spaceBetween else 0.dp,
                        end = if (i < numOfStars) spaceBetween else 0.dp
                    )
                    .size(size = size)
                    .testTag("RatingStar"),
                painterEmpty = painterEmpty, painterFilled = painterFilled
            )
        }
    }
}
