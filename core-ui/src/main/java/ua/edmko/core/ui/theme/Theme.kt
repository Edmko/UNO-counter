package ua.edmko.core.ui.theme

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

@Stable
class Colors(
    primary: Color = Color.Red,
    secondary: Color = Color.Red,
    surface: Color = DarkGrey,
    onSurface: Color = Color.White,
    onPrimary: Color = DarkGrey,
    background: Color = Color.Black,
) {
    var primary by mutableStateOf(primary, structuralEqualityPolicy())
        internal set
    var secondary by mutableStateOf(secondary, structuralEqualityPolicy())
        internal set
    var background by mutableStateOf(background, structuralEqualityPolicy())
        internal set
    var surface by mutableStateOf(surface, structuralEqualityPolicy())
        internal set
    var onPrimary by mutableStateOf(onPrimary, structuralEqualityPolicy())
        internal set
    var onSurface by mutableStateOf(onSurface, structuralEqualityPolicy())
        internal set

    fun copy(
        primary: Color = this.primary,
        secondary: Color = this.secondary,
        background: Color = this.background,
        surface: Color = this.surface,
        onPrimary: Color = this.onPrimary,
        onSurface: Color = this.onSurface,
    ): Colors =
        Colors(
            primary,
            secondary,
            background,
            surface,
            onPrimary,
            onSurface,
        )

    override fun toString(): String {
        return "Colors(" +
            "primary=$primary, " +
            "secondary=$secondary," +
            " background=$background, " +
            "surface=$surface, " +
            "onPrimary=$onPrimary, " +
            "onSurface=$onSurface)"
    }
}

@Composable
fun getAppRadioButtonColors() =
    RadioButtonDefaults.colors(
        selectedColor = AppTheme.colors.primary,
        unselectedColor = AppTheme.colors.onSurface.copy(alpha = 0.6f),
        disabledColor = AppTheme.colors.onSurface.copy(ContentAlpha.disabled),
    )

@Composable
fun getCheckboxColors() =
    CheckboxDefaults.colors(
        checkedColor = AppTheme.colors.primary,
        uncheckedColor = AppTheme.colors.onSurface.copy(alpha = 0.6f),
        checkmarkColor = AppTheme.colors.surface,
        disabledColor = AppTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
        disabledIndeterminateColor = AppTheme.colors.primary.copy(alpha = ContentAlpha.disabled),
    )

@Composable
fun getButtonColors() =
    ButtonDefaults.buttonColors(
        backgroundColor = AppTheme.colors.primary,
        contentColor = AppTheme.colors.onPrimary,
        disabledBackgroundColor = AppTheme.colors.onSurface.copy(alpha = 0.12f)
            .compositeOver(AppTheme.colors.surface),
        disabledContentColor = AppTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
    )

@Composable
fun AppTheme(
    colors: Colors = Colors(),
    typography: Typography = Typography,
    shapes: Shapes = Shapes,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalTypography provides typography,
        LocalShapes provides shapes,
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

internal val LocalTypography = staticCompositionLocalOf { Typography }

internal val LocalShapes = staticCompositionLocalOf { Shapes() }

internal val LocalAppColors = staticCompositionLocalOf { Colors() }
