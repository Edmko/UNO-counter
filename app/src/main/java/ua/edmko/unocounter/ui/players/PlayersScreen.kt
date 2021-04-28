package ua.edmko.unocounter.ui.players

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ua.edmko.unocounter.ui.theme.UNOcounterTheme

@Composable
fun PlayersScreen(viewModel: PlayersViewModel){
    UNOcounterTheme {
        Box(Modifier.background(Color.Black).fillMaxSize()) {
            Text("Players screen", modifier = Modifier.align(Alignment.Center), color = Color.White)
        }
    }
}