package ua.edmko.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class Colors(
    val primary: Color = Color.Red,
    val secondary: Color = Color.White,
    val background: Color = Color.Black,
    val onBackground: Color = Color.White
)

private val DarkAppColors = Colors(
    primary = Color.Red,
    secondary = Color.Red,
    background = Color.Black,
    onBackground = Color.White
)

@Composable
fun AppTheme(
    colors: Colors = DarkAppColors,
    typography: Typography = Typography,
    shapes: Shapes = Shapes,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalTypography provides typography,
        LocalShapes provides shapes
    ) {
        content()
        MaterialTheme
    }
}

object AppTheme {

    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}

internal val LocalTypography = staticCompositionLocalOf { Typography() }

internal val LocalShapes = staticCompositionLocalOf { Shapes() }

internal val LocalAppColors = staticCompositionLocalOf { Colors() }