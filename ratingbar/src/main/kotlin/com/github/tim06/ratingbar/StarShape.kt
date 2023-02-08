package com.github.tim06.ratingbar

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

val StarShape = object : Shape {
    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection, density: Density
    ): Outline {
        return Outline.Generic(Path().apply {

            val outerRadius = size.minDimension * 0.5f
            val innerRadius = size.minDimension * 0.2f

            val centerX = size.width / 2
            val centerY = size.height / 2

            var totalAngle = PI / 2
            val degreesPerSection = (2 * PI) / 5

            moveTo(centerX, 0f)

            var x: Double
            var y: Double

            for (i in 1..5) {
                totalAngle += degreesPerSection / 2
                x = centerX + cos(totalAngle) * innerRadius
                y = centerY - sin(totalAngle) * innerRadius
                lineTo(x.toFloat(), y.toFloat())

                totalAngle += degreesPerSection / 2
                x = centerX + cos(totalAngle) * outerRadius
                y = centerY - sin(totalAngle) * outerRadius
                lineTo(x.toFloat(), y.toFloat())
            }

            close()
        })
    }
}