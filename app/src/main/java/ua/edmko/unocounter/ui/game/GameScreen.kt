package ua.edmko.unocounter.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import ua.edmko.unocounter.R
import ua.edmko.unocounter.domain.entities.Game
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.domain.entities.Round
import ua.edmko.unocounter.domain.entities.Round.Companion.getRoundStub
import ua.edmko.unocounter.ui.components.EditDialog
import ua.edmko.unocounter.ui.components.GameButton
import ua.edmko.unocounter.ui.components.PlayerItem
import ua.edmko.unocounter.ui.components.Toolbar
import ua.edmko.unocounter.ui.theme.UNOcounterTheme
import ua.edmko.unocounter.ui.theme.baseDimension

@Composable
fun GameScreen(viewModel: GameViewModel, gameId: String?) {

    viewModel.fetchGame(gameId ?: "")
    val state by viewModel.viewStates().collectAsState()

    UNOcounterTheme() {
        Scaffold(
            topBar = { Toolbar(title = "Game") { viewModel.obtainEvent(NavigateBack) } },
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) { paddings ->
            Box(
                Modifier
                    .padding(paddings)
                    .background(Color.Black)
                    .fillMaxSize()

            ) {
                GameScreen(state, viewModel::obtainEvent)
            }
        }
    }
}

@Composable
fun GameScreen(state: GameViewState?, event: (GameEvent) -> Unit) {

    if (state?.isDialogShows == true) EditDialog(
        title = stringResource(
            id = R.string.score_for_player, state.selectedPlayer?.name ?: ""
        ),
        textType = KeyboardOptions(keyboardType = KeyboardType.Number),
        onClick = { event.invoke(ConfirmEdition(it.toIntOrNull() ?: 0)) },
        dismiss = { event.invoke(DismissDialog) })

        Box(Modifier.fillMaxSize()) {
            PlayersList(
                state?.game,
                currentRound = state?.currentRound
            ) { event.invoke(EditScore(it)) }

            GameButton(
                text = stringResource(R.string.next_round),
                modifier = Modifier
                    .padding(baseDimension, 0.dp, baseDimension, 40.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter),
                onClick = { event.invoke(NextRound) })
        }
}

@Composable
fun PlayersList(game: Game?, currentRound: Round?, onClick: (Player) -> Unit) {
    LazyColumn() {
        game?.players?.let { players ->
            itemsIndexed(players) { index, player ->
                var total = 0
                game.rounds.forEach { round -> total += round.result[player.playerId] ?: 0 }
                PlayerItem(
                    modifier = Modifier.clickable { onClick.invoke(player) },
                    name = player.name,
                    color = Color.Green
                ) {
                    Text(
                        modifier = Modifier.padding(start = 18.dp),
                        text = total.toString(),
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(start = 40.dp),
                        text = currentRound?.result?.getOrDefault(player.playerId, 0)
                            .toString(),
                        color = Color.White
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    UNOcounterTheme() {
        GameScreen(state = GameViewState()) {}
    }
}

@Preview
@Composable
fun PlayersListPreview() {
    UNOcounterTheme() {
        Surface() {
            PlayersList(Game.getGameStub(), getRoundStub()) {}
        }
    }
}