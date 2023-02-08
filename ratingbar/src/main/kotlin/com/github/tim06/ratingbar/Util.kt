package com.github.tim06.ratingbar

import java.math.RoundingMode
import java.text.DecimalFormat

internal fun Float.roundCeilDown(): Double {
    val df = DecimalFormat("#")
    df.roundingMode = RoundingMode.DOWN
    return df.format(this).toDouble()
}

internal fun Float.roundCeilUp(): Double {
    val df = DecimalFormat("#")
    df.roundingMode = RoundingMode.UP
    return df.format(this).toDouble()
}

internal fun Float.roundDown(): Double {
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.DOWN
    return df.format(this).toDouble()
}

internal fun Double.roundDown(): Double {
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.DOWN
    return df.format(this).toDouble()
}

internal fun Double.roundUp(): Double {
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.UP
    return df.format(this).toDouble()
}

const val RATING_STAR_TEST_TAG = "RATING_STAR_TEST_TAG"