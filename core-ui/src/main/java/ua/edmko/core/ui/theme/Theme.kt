package ua.edmko.core.ui.theme

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class Colors(
    val primary: Color = Color.Red,
    val secondary: Color = Color.White,
    val surface: Color = DarkGrey,
    val onSurface: Color = Color.White,
    val onPrimary: Color = DarkGrey
)


@Composable
fun getAppRadioButtonColors() = RadioButtonDefaults.colors(
    selectedColor = AppTheme.colors.primary,
    unselectedColor = AppTheme.colors.onSurface.copy(alpha = 0.6f),
    disabledColor = AppTheme.colors.onSurface.copy(ContentAlpha.disabled)
)

@Composable
fun getCheckboxColors() = CheckboxDefaults.colors(
    checkedColor = AppTheme.colors.primary,
    uncheckedColor = AppTheme.colors.onSurface.copy(alpha = 0.6f),
    checkmarkColor = AppTheme.colors.surface,
    disabledColor = AppTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
    disabledIndeterminateColor = AppTheme.colors.primary.copy(alpha = ContentAlpha.disabled)
)

@Composable
fun AppTheme(
    colors: Colors = Colors(),
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