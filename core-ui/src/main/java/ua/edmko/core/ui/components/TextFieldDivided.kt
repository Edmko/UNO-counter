package ua.edmko.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edmko.core.ui.theme.AppTheme

@Composable
fun TextFieldDivided(
    modifier: Modifier = Modifier,
    description: String,
    value: String,
    click: (() -> Unit)? = null,
) {
    Column(
        modifier
            .fillMaxWidth()
            .clickable { click?.invoke() }
            .padding(top = 15.dp, start = 18.dp, end = 18.dp),
    ) {
        Text(
            style = AppTheme.typography.h5,
            color = AppTheme.colors.onSurface,
            text = "$description: $value",
        )
        Divider(Modifier.padding(top = 10.dp), color = AppTheme.colors.onSurface)
    }
}

@Preview
@Composable
fun TextFieldWithDividerPreview() {
    AppTheme {
        Surface(
            color = AppTheme.colors.surface,
        ) {
            TextFieldDivided(value = "500", description = "Goal")
        }
    }
}
