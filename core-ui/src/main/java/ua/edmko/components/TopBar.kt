package ua.edmko.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edmko.theme.UnoCounterTheme

@Composable
fun Toolbar(title: String, back: () -> Unit) {
    Surface() {
        Row(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Navigate back",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable(onClick = back)
                    .padding(18.dp)
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = title,
                style = MaterialTheme.typography.h5
            )
        }
    }
}

@Preview
@Composable
fun ToolbarPreview() {
    UnoCounterTheme {
        Toolbar("Toolbar title") {}
    }
}