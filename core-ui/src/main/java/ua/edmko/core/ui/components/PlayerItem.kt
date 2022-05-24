package ua.edmko.core.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ua.edmko.core.ui.theme.AppTheme

/**
 * @param statistics get second half of row space
 */
@Composable
fun PlayerItem(
    modifier: Modifier = Modifier,
    name: String, color: Color,
    height: Dp = 60.dp,
    statistics: @Composable RowScope.() -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Icon(
            Icons.Rounded.Person,
            contentDescription = null,
            modifier = Modifier
                .padding(0.dp, 5.dp, 10.dp, 5.dp)
                .fillMaxHeight()
                .fillMaxWidth(0.1f),
            tint = color
        )
        Text(
            text = name,
            style = AppTheme.typography.body1,
            color = color,
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .align(Alignment.CenterVertically)
        )
        statistics()
    }
}

@Preview
@Composable
fun PlayerItemPreview() {
    AppTheme {
        Surface() {
            PlayerItem(modifier = Modifier, "John Smith", color = Color.Red)
        }
    }
}