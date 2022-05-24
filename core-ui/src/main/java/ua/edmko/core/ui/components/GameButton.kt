package ua.edmko.core.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.edmko.core.ui.theme.AppTheme
import ua.edmko.core.ui.theme.getButtonColors

@Composable
fun GameButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    text: String, onClick: () -> Unit,
    fontSize: TextUnit = 40.sp
) {
    Button(
        onClick = onClick,
        shape = AppTheme.shapes.medium,
        modifier = modifier,
        colors = getButtonColors(),
        contentPadding = PaddingValues(0.dp),
        enabled = isEnabled
    ) {
        Text(
            text,
            color = AppTheme.colors.onPrimary,
            fontSize = fontSize,
            fontWeight = FontWeight.Black,
        )
    }
}

@Preview
@Composable
fun GameButtonPreview() {
    AppTheme {
        Surface() {
            GameButton(text = "Start game", onClick = {})
        }
    }
}