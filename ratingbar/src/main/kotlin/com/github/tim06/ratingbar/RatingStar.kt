package com.github.tim06.ratingbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Star item
 * @param filledWidth filled part of star
 * @param borderWidth star border width
 * @param borderColor border color
 * @param backgroundColor background color for inactive star
 * @param filledColor star fill color
 */
@Composable
fun RatingStar(
    modifier: Modifier = Modifier,
    filledWidth: Double = 0.0,
    borderWidth: Dp = 1.dp,
    borderColor: Color = Color.Yellow,
    backgroundColor: Color = Color.Transparent,
    filledColor: Color = Color.Red
) {
    Box(modifier = modifier
        .clip(StarShape)
        .background(color = backgroundColor)
        .border(width = borderWidth, color = borderColor, shape = StarShape)
        .drawWithContent {
            drawContent()
            drawRect(
                color = filledColor, size = size.copy(filledWidth.toFloat())
            )
        }
        .testTag(RATING_STAR_TEST_TAG))
}

@Preview
@Composable
private fun RatingStarPreview() {
    RatingStar(modifier = Modifier.size(48.dp))
}