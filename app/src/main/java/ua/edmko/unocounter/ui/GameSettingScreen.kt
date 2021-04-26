package ua.edmko.unocounter.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.edmko.unocounter.R
import ua.edmko.unocounter.ui.theme.UNOcounterTheme
import ua.edmko.unocounter.ui.theme.baseDimension

@Composable
fun GameSettingScreen(viewModel: GameSettingViewModel) {
    UNOcounterTheme() {
        Box(Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .padding(0.dp, 40.dp, 0.dp, 0.dp)
                    .fillMaxSize(),
            ) {
                TextFieldWithDivider(stringResource(R.string.goal))
                TextFieldWithDivider(stringResource(R.string.type))
                Text(
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    text = stringResource(R.string.players),
                    modifier = Modifier.padding(baseDimension, 32.dp, baseDimension, 18.dp)
                )
                Divider(color = Color.White)
                Box(Modifier.weight(1f)) {
                    LazyColumn(
                        Modifier
                            .fillMaxSize()
                            .padding(baseDimension, 0.dp, baseDimension, 0.dp),
                        contentPadding = PaddingValues(bottom = 110.dp)
                    ) {
                        itemsIndexed(players) { index, player ->
                            val color = when (index % 4) {
                                0 -> Color.Yellow
                                1 -> Color.Red
                                2 -> Color.Blue
                                else -> Color.Green
                            }
                            PlayerItem(player = player, color)
                        }
                    }
                }
            }
            Button(
                onClick = {},
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(baseDimension, 0.dp, baseDimension, 40.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter),
                colors = buttonColors(backgroundColor = Color.Red),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    stringResource(R.string.start_game),
                    color = Color.Black,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}

@Preview
@Composable
fun PlayerItem(player: String = "John Smith", color: Color = Color.Red) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            Icons.Rounded.Person,
            contentDescription = "Avatar",
            modifier = Modifier
                .padding(0.dp, 5.dp, 10.dp, 5.dp)
                .size(40.dp),
            tint = color
        )
        Text(
            text = player,
            style = MaterialTheme.typography.body1,
            color = color
        )
    }
}

@Composable
fun TextFieldWithDivider(initText: String) {
    var text by remember { mutableStateOf(initText) }
    Column() {
        Text(
            style = MaterialTheme.typography.h5,
            color = Color.White,
            text = initText,
            modifier = Modifier.padding(baseDimension)
        )
        Divider(
            Modifier
                .fillMaxWidth()
                .padding(baseDimension, 10.dp, baseDimension, 0.dp), color = Color.White
        )
    }

}

val players = listOf(
    "John Smith",
    "Dali Bali",
    "Man Quite",
    "Vasya",
    "John Smith",
    "Dali Bali",
    "Man Quite",
    "Vasya",
    "John Smith",
    "Dali Bali",
    "Man Quite",
    "Vasya"
)