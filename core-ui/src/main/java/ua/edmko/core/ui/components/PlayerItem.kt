package ua.edmko.core.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import ua.edmko.core.ui.theme.AppTheme

private const val PLAYER_ITEM_ICON_FRACTION = 0.1F
private const val PLAYER_ITEM_NAME_FRACTION = 0.4F
const val PLAYER_ITEM_MAIN_FRACTION = PLAYER_ITEM_ICON_FRACTION + PLAYER_ITEM_NAME_FRACTION

/**
 * @param statistics get second half of row space
 */
@Composable
fun PlayerItem(
    modifier: Modifier = Modifier,
    name: String,
    color: Color,
    statistics: @Composable RowScope.() -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
    ) {
        Icon(
            imageVector = Icons.Rounded.Person,
            contentDescription = null,
            modifier = Modifier
                .padding(0.dp, 5.dp, 10.dp, 5.dp)
                .fillMaxHeight()
                .fillMaxWidth(PLAYER_ITEM_ICON_FRACTION),
            tint = color,
        )
        Text(
            text = name,
            style = AppTheme.typography.body1,
            color = color,
            modifier = Modifier
                .fillMaxWidth(PLAYER_ITEM_NAME_FRACTION)
                .align(Alignment.CenterVertically),
        )
        statistics()
    }
}

@Preview
@Composable
fun PlayerItemPreview() {
    AppTheme {
        Surface {
            PlayerItem(modifier = Modifier, "John Smith", color = Color.Red)
        }
    }
}
