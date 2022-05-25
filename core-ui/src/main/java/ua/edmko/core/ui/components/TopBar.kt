package ua.edmko.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edmko.core.ui.theme.AppTheme
import ua.edmko.core.ui.theme.baseHorizontalPadding

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String,
    content: (@Composable RowScope.() -> Unit)? = null,
    back: (() -> Unit)? = null,
) {
    Surface(
        modifier = modifier.statusBarsPadding(),
        color = AppTheme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .padding(start = baseHorizontalPadding),
            verticalAlignment = Alignment.CenterVertically

        ) {
            back?.let { back ->
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Navigate back",
                    modifier = Modifier
                        .clickable(onClick = back)
                        .padding(end = 18.dp),
                    tint = AppTheme.colors.onSurface
                )
            }
            Text(
                modifier = Modifier
                    .weight(1f),
                text = title,
                style = AppTheme.typography.h5,
                color = AppTheme.colors.onSurface
            )
            content?.invoke(this)
        }
    }
}

@Preview
@Composable
fun ToolbarPreview() {
    AppTheme {
        Toolbar(title = "Toolbar title", content = {}, back = {})
    }
}