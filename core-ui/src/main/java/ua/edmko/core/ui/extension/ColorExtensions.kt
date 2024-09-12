package ua.edmko.core.ui.extension

import androidx.compose.ui.graphics.Color
import ua.edmko.core.ui.theme.Orange

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
