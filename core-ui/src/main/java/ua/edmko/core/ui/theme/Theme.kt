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
import ua.edmko.domain.entities.Theme

@Stable
class Colors(
    private val theme: Theme = Theme.LIGHT,
    primary: Color = Color.Red,
    secondary: Color = Color.Red,
    surface: Color = if (theme == Theme.DARK) DarkGrey else Color.White,
    onSurface: Color = if (theme == Theme.DARK) Color.White else Color.Black,
    onPrimary: Color = if (theme == Theme.DARK) DarkGrey else Color.White,
    background: Color = if (theme == Theme.DARK) Color.Black else Color.White,
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
        theme: Theme = this.theme,
        primary: Color = this.primary,
        secondary: Color = this.secondary,
        background: Color = this.background,
        surface: Color = this.surface,
        onPrimary: Color = this.onPrimary,
        onSurface: Color = this.onSurface,
    ): Colors =
        Colors(
            theme,
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
        disabledBackgroundColor = AppTheme.colors.onSurface
            .copy(alpha = 0.12f)
            .compositeOver(AppTheme.colors.surface),
        disabledContentColor = AppTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
    )

@Composable
fun AppTheme(
    theme: Theme = Theme.DARK,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalAppColors provides if (theme == Theme.DARK) darkThemeColorScheme() else lightThemeColorScheme(),
        LocalTypography provides Typography,
        LocalShapes provides Shapes,
    ) {
        content()
    }
}

private fun lightThemeColorScheme() = Colors(
    primary = Color.Red,
    secondary = Color.Red,
    surface = LightLightGrey,
    onSurface = Color.Black,
    onPrimary = Color.White,
    background = Color.White,
)

private fun darkThemeColorScheme() = Colors(
    primary = Color.Red,
    secondary = Color.Red,
    surface = LightGrey,
    onSurface = Color.White,
    onPrimary = DarkGrey,
    background = Color.Black,
)

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
