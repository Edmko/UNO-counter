package ua.edmko.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edmko.core.theme.UnoCounterTheme

@Composable
fun TextFieldDivided(
    modifier: Modifier = Modifier,
    description: String,
    value: String,
    click: (() -> Unit)? = null
) {
    Column(
        modifier
            .fillMaxWidth()
            .clickable { click?.invoke() }
            .padding(top = 15.dp, start = 18.dp, end = 18.dp)
    ) {
        Text(
            style = MaterialTheme.typography.h5,
            color = Color.White,
            text = "$description: $value",
        )
        Divider(Modifier.padding(top = 10.dp), color = MaterialTheme.colors.onSurface)
    }

}

@Preview
@Composable
fun TextFieldWithDividerPreview() {
    UnoCounterTheme() {
        Surface() {
            TextFieldDivided(value = "500", description = "Goal")
        }
    }
}