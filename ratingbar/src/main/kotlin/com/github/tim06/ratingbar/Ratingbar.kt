package com.github.tim06.ratingbar

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.abs

/**
 * Layout for rating stars
 *
 * @param count stars count
 * @param borderWidth star border width
 * @param borderColor border color
 * @param backgroundColor background color for inactive star
 * @param filledColor star fill color
 * @param tapEnabled is need listen taps
 * @param dragEnabled is need listen user drag
 * @param selectAnimation animation that will be used to change the value through time
 * @param onRatingSelected callback for listen selected stars count
 */
@Composable
fun Ratingbar(
    modifier: Modifier = Modifier,
    count: Int = 5,
    currentRating: Double = 0.0,
    borderWidth: Dp = 1.dp,
    borderColor: Color = Color.Yellow,
    backgroundColor: Color = Color.Transparent,
    filledColor: Color = Color.Red,
    tapEnabled: Boolean = true,
    dragEnabled: Boolean = true,
    selectAnimation: AnimationSpec<Float> = spring(),
    onRatingSelected: (Double) -> Unit = {}
) {
    var starWidth by remember { mutableStateOf(0) }
    var isTap by remember { mutableStateOf(false) }
    var tapPoint by remember { mutableStateOf(Offset.Zero) }

    // Offset for current rating
    val initialRating by remember(starWidth, currentRating) {
        derivedStateOf {
            starWidth * currentRating - 1
        }
    }

    // Animated offset for current rating
    val animatedInitialRating by animateFloatAsState(
        targetValue = initialRating.toFloat(), animationSpec = selectAnimation
    )

    // Rating in ceil double, like 4.0 or 7.0
    val ratingInt by remember(animatedInitialRating) {
        derivedStateOf<Double> {
            val filledCount =
                (animatedInitialRating / starWidth).toDouble().coerceIn(0.0..count.toDouble())
            filledCount.toFloat().roundCeilDown()
        }
    }

    // Rating in double, like 4.4 or 7.2
    val ratingDouble by remember(animatedInitialRating, ratingInt) {
        derivedStateOf<Double> {
            abs(animatedInitialRating - (starWidth * ratingInt))
        }
    }

    // Callback for notify
    LaunchedEffect(isTap, tapPoint, onRatingSelected) {
        val filledCount = (tapPoint.x / starWidth).coerceIn(0.0f..count.toFloat())
        if (tapPoint != Offset.Zero) {
            if (isTap) {
                onRatingSelected.invoke(filledCount.roundCeilUp())
            } else {
                onRatingSelected.invoke(filledCount.roundDown())
            }
        }
    }

    Layout(modifier = modifier.then(
        Modifier
            .pointerInput(Unit) {
                if (tapEnabled) {
                    detectTapGestures { offset ->
                        tapPoint = offset
                        isTap = true
                    }
                }
            }
            .pointerInput(Unit) {
                if (dragEnabled) {
                    detectDragGestures(onDragStart = { offset ->
                        tapPoint = offset
                        isTap = false
                    }) { _, dragAmount ->
                        tapPoint += dragAmount
                    }
                }
            }), content = {
        repeat(count) { index ->
            RatingStar(
                borderWidth = borderWidth,
                borderColor = borderColor,
                backgroundColor = backgroundColor,
                filledColor = filledColor,
                filledWidth = if (ratingInt.toInt() == index) {
                    if (isTap) {
                        starWidth.toDouble()
                    } else {
                        ratingDouble
                    }
                } else if (ratingInt > index) {
                    starWidth.toDouble()
                } else {
                    0.0
                }
            )
        }
    }) { measurables, constraints ->
        starWidth = constraints.maxWidth / count
        val childConstraints = constraints.copy(
            minWidth = minOf(constraints.minWidth, starWidth),
            maxWidth = starWidth,
            minHeight = starWidth,
            maxHeight = starWidth
        )
        val placeables = measurables.map { measurable ->
            measurable.measure(childConstraints)
        }

        layout(constraints.maxWidth, childConstraints.maxHeight) {
            var xPosition = 0

            placeables.forEach { placeable ->
                placeable.placeRelative(x = xPosition, y = 0)
                xPosition += placeable.width
            }
        }
    }
}

@Preview
@Composable
private fun RatingBarPreview() {
    Ratingbar(modifier = Modifier.fillMaxWidth())
}