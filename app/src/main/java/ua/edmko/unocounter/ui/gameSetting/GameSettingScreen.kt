package ua.edmko.unocounter.ui.gameSetting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import ua.edmko.unocounter.R
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.domain.entities.Player.Companion.getPlayersStub
import ua.edmko.unocounter.ui.components.EditDialog
import ua.edmko.unocounter.ui.components.GameButton
import ua.edmko.unocounter.ui.components.PlayerItem
import ua.edmko.unocounter.ui.components.TextFieldDivided
import ua.edmko.unocounter.ui.theme.UNOcounterTheme
import ua.edmko.unocounter.ui.theme.baseDp
import ua.edmko.unocounter.utils.getColorByIndex

@Composable
fun GameSettingScreen(viewModel: GameSettingViewModel) {
    val state by viewModel.viewStates().collectAsState()
    viewModel.fetchPlayers()
    UNOcounterTheme {
        GameSettingContent(state, viewModel::obtainEvent)
    }
}

@Composable
fun GameSettingContent(state: GameSettingViewState?, event: (GameSettingEvent) -> Unit) {
    Surface(
        modifier = Modifier.statusBarsPadding()
    ) {
        //dialog
        if (state?.dialogShows == true) EditDialog(
            textType = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            title = stringResource(id = R.string.insert_goal),
            onDismiss = { event(DismissDialog) }
        ) { text -> event(ChangeGoal(text.toIntOrNull() ?: 0)) }

        //content
        Box(Modifier.fillMaxSize()) {

            Column(
                Modifier.fillMaxSize()
            ) {
                val goal = state?.goal.toString()
                TextFieldDivided(
                    modifier = Modifier,
                    stringResource(id = R.string.goal),
                    goal
                ) { event(OnGoalClickEvent) }
                TextFieldDivided(
                    modifier = Modifier,
                    stringResource(R.string.type),
                    "Classic"
                )
                Text(
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    text = stringResource(R.string.players),
                    modifier = Modifier.padding(baseDp, 32.dp, baseDp, 18.dp)
                )
                Divider(color = MaterialTheme.colors.onSurface)
                PlayersList(modifier = Modifier.weight(1f), players = state?.players)
            }

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 110.dp, end = baseDp),
                backgroundColor = MaterialTheme.colors.primary,
                onClick = { event(EditPlayers) }) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "Edit players",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center),
                    tint = MaterialTheme.colors.surface
                )

            }

            GameButton(
                text = stringResource(R.string.start_game),
                modifier = Modifier
                    .padding(baseDp, 0.dp, baseDp, 40.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter),
                isEnabled = state?.players?.isNotEmpty()?:false,
                onClick = { event(StartGame) })
        }
    }
}

@Composable
fun PlayersList(modifier: Modifier = Modifier, players: List<Player>?) {
    Box(modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(baseDp, 0.dp, baseDp, 0.dp),
            contentPadding = PaddingValues(bottom = 110.dp)
        ) {
            players?.let {
                itemsIndexed(it) { index, player ->
                    val color = getColorByIndex(index)
                    PlayerItem(name = player.name, color = color)
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



