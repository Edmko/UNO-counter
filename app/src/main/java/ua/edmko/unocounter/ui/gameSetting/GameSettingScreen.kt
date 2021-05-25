package ua.edmko.unocounter.ui.gameSetting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.statusBarsPadding
import ua.edmko.unocounter.R
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.domain.entities.Player.Companion.getPlayersStub
import ua.edmko.unocounter.ui.components.EditDialog
import ua.edmko.unocounter.ui.theme.UNOcounterTheme
import ua.edmko.unocounter.ui.theme.baseDimension

@Composable
fun GameSettingScreen(viewModel: GameSettingViewModel) {
    val state by viewModel.viewStates().collectAsState()
    viewModel.fetchPlayers()
    UNOcounterTheme {
        GameSettingContent(state = state) { viewEvent ->
            viewModel.obtainEvent(viewEvent)
        }
    }
}

@Composable
fun GameSettingContent(state: GameSettingViewState?, event: (GameEvent) -> Unit) {
    //dialog
    if (state?.dialogShows == true) EditDialog(
        textType = KeyboardOptions(keyboardType = KeyboardType.Number),
        title = stringResource(id = R.string.insert_goal),
        dismiss = { event.invoke(DismissDialog) }
    ) { text -> event.invoke(ChangeGoal(text.toIntOrNull() ?: 0)) }
    Surface(
        modifier = Modifier.statusBarsPadding()
    ) {
        Box(Modifier.fillMaxSize()) {
            Column(
                Modifier.fillMaxSize()
            ) {
                val goal = state?.goal.toString()
                TextFieldWithDivider(stringResource(id = R.string.goal), goal) {
                    event.invoke(
                        OnGoalClickEvent
                    )
                }
                TextFieldWithDivider(stringResource(R.string.type), "Normal")
                Text(
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    text = stringResource(R.string.players),
                    modifier = Modifier.padding(baseDimension, 32.dp, baseDimension, 18.dp)
                )
                Divider(color = Color.White)
                PlayersList(modifier = Modifier.weight(1f), players = state?.players)
            }
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 110.dp, end = baseDimension),
                backgroundColor = Color.Red,
                onClick = { event.invoke(EditPlayers) }) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "Edit",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center),
                    tint = Color.Black
                )

            }
            GameButton(
                text = stringResource(R.string.start_game),
                modifier = Modifier
                    .padding(baseDimension, 0.dp, baseDimension, 40.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun PlayersList(modifier: Modifier = Modifier, players: List<Player>?) {
    Box(modifier) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(baseDimension, 0.dp, baseDimension, 0.dp),
            contentPadding = PaddingValues(bottom = 110.dp)
        ) {
            players?.let {
                itemsIndexed(it) { index, player ->
                    val color = when (index % 4) {
                        0 -> Color.Yellow
                        1 -> Color.Red
                        2 -> Color.Blue
                        else -> Color.Green
                    }
                    PlayerItem(player = player.name, color)
                }
            }
        }
    }
}

@Preview
@Composable
fun GameSettingContentPreview() {
    UNOcounterTheme() {
        GameSettingContent(state = GameSettingViewState(players = getPlayersStub())) {
        }
    }
}

@Composable
fun PlayerItem(player: String, color: Color) {
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

@Preview
@Composable
fun PlayerItemPreview() {
    UNOcounterTheme() {
        Surface() {
            PlayerItem("John Smith", color = Color.Red)
        }
    }
}

@Composable
fun GameButton(text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        shape = RoundedCornerShape(10.dp),
        modifier = modifier,
        colors = buttonColors(backgroundColor = Color.Red),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text,
            color = Color.Black,
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Black
        )
    }
}

@Preview
@Composable
fun GameButtonPreview() {
    Surface() {
        GameButton(text = "Start game")
    }
}

@Composable
fun TextFieldWithDivider(description: String, value: String, click: (() -> Unit)? = null) {
    Column(Modifier.clickable {
        click?.invoke()
    }) {
        Row() {

        }
        Text(
            style = MaterialTheme.typography.h5,
            color = Color.White,
            text = "$description: $value",
            modifier = Modifier.padding(baseDimension)
        )
        Divider(
            Modifier
                .fillMaxWidth()
                .padding(baseDimension, 10.dp, baseDimension, 0.dp), color = Color.White
        )
    }

}

@Preview
@Composable
fun TextFieldWithDividerPreview() {
    UNOcounterTheme() {
        Surface() {
            TextFieldWithDivider(value = "500", description = "Goal")
        }
    }
}

