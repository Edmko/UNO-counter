package ua.edmko.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edmko.core.ui.R
import ua.edmko.core.ui.theme.AppTheme
import ua.edmko.core.ui.theme.horizontalPadding

@Composable
fun Toolbar(
    title: String,
    content: (@Composable RowScope.() -> Unit)? = null,
    back: (() -> Unit)? = null,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        color = AppTheme.colors.background,
    ) {
        Row(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            back?.let { back ->
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.navigate_back_content_description),
                    modifier = Modifier
                        .clickable(onClick = back)
                        .padding(end = 18.dp),
                    tint = AppTheme.colors.onSurface,
                )
            }
            Text(
                modifier = Modifier
                    .weight(1f),
                text = title,
                style = AppTheme.typography.h5,
                color = AppTheme.colors.onSurface,
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
