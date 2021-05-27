package ua.edmko.unocounter.ui.game

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edmko.unocounter.R
import ua.edmko.unocounter.domain.entities.Game
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.domain.entities.Round
import ua.edmko.unocounter.domain.entities.Round.Companion.getRoundStub
import ua.edmko.unocounter.ui.components.EditDialog
import ua.edmko.unocounter.ui.components.PlayerItem
import ua.edmko.unocounter.ui.theme.UNOcounterTheme

@Composable
fun GameScreen(viewModel: GameViewModel) {
    val state by viewModel.viewStates().collectAsState()
    UNOcounterTheme() {
        GameScreen(state, viewModel::obtainEvent)
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
    PlayersList(state?.game, currentRound = state?.currentRound) { event.invoke(EditScore(it)) }
}

@Composable
fun PlayersList(game: Game?, currentRound: Round?,  onClick: (Player) -> Unit) {
    LazyColumn() {
        game?.players?.let { players ->
            itemsIndexed(players) { index, player ->
                var total = 0
                game.rounds.forEach { round -> total += round.result[player.playerId] ?: 0 }
                PlayerItem(modifier = Modifier.clickable { onClick.invoke(player) }, name = player.name, color = Color.Green) {
                    Text(
                        modifier = Modifier.padding(start = 18.dp),
                        text = total.toString(),
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(start = 40.dp),
                        text = currentRound?.result?.getOrDefault(player.playerId, 0).toString(),
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