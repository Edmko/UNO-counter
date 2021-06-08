package ua.edmko.unocounter.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ua.edmko.unocounter.ui.endgane.EndGameViewModel
import ua.edmko.unocounter.ui.theme.UNOcounterTheme

@Composable
fun EndGameScreen(viewModel: EndGameViewModel, playerName: String) {
    UNOcounterTheme() {
        Surface() {
            Box(Modifier.fillMaxSize()) {
                Column(Modifier.align(Alignment.Center)) {
                    Text(text = "Congratulation", style = MaterialTheme.typography.h4)
                    Text(
                        text = playerName,
                        Modifier.padding(top = 10.dp).align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.h4,
                        color = Color.Red,
                    )
                }
            }
        }
    }

}