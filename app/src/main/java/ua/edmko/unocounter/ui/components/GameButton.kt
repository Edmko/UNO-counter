package ua.edmko.unocounter.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.edmko.unocounter.ui.theme.UNOcounterTheme

@Composable
fun GameButton(modifier: Modifier = Modifier, isEnabled: Boolean = true , text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(),
        contentPadding = PaddingValues(0.dp),
        enabled = isEnabled
    ) {
        Text(
            text,
            color = MaterialTheme.colors.surface,
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Black
        )
    }
}

@Preview
@Composable
fun GameButtonPreview() {
    UNOcounterTheme() {
        Surface() {
            GameButton(text = "Start game", onClick = {})
        }
    }

}