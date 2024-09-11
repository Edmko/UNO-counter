package ua.edmko.core.extension

import androidx.compose.ui.graphics.Color

/**
 * Color selection by place in list
 */
fun Int.getColorByIndex(): Color {
    return when (this % 4) {
        0 -> Orange
        1 -> Color.Red
        2 -> Color.Blue
        else -> Color.Green
    }
}

val Orange = Color(0xFFFFA500)
