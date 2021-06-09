package ua.edmko.unocounter.utils

import androidx.compose.ui.graphics.Color

fun getColorByIndex(index: Int): Color{
    return when (index % 4) {
        0 -> Color.Yellow
        1 -> Color.Red
        2 -> Color.Blue
        else -> Color.Green
    }
}