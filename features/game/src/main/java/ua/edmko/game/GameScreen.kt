package ua.edmko.game

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ua.edmko.core.ui.components.EditDialog
import ua.edmko.core.ui.components.GameButton
import ua.edmko.core.ui.components.PLAYER_ITEM_MAIN_FRACTION
import ua.edmko.core.ui.components.PlayerItem
import ua.edmko.core.ui.components.Toolbar
import ua.edmko.core.ui.extension.getColorByIndex
import ua.edmko.core.ui.theme.AppTheme
import ua.edmko.core.ui.theme.getAppRadioButtonColors
import ua.edmko.core.ui.theme.horizontalPadding
import ua.edmko.domain.entities.Game
import ua.edmko.domain.entities.Player
import ua.edmko.domain.entities.PlayerId
import ua.edmko.domain.entities.Round
import ua.edmko.domain.entities.Score

@Composable
fun GameScreen() {
    val viewModel: GameViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.initialize()
    }
    BackHandler {
        viewModel.obtainEvent(NavigateBack)
    }
    val state by viewModel.viewStates().collectAsState()
    state?.let { GameScreen(it, viewModel::obtainEvent) }
}

@Composable
internal fun GameScreen(state: GameViewState, event: (GameEvent) -> Unit) {
    GameDialogs(state, event)
    Scaffold(
        topBar = { Toolbar(title = stringResource(R.string.game)) { event(NavigateBack) } },
        modifier = Modifier,
        backgroundColor = AppTheme.colors.background,
        bottomBar = {
            GameButton(
                text = stringResource(R.string.next_round),
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(horizontalPadding, 0.dp, horizontalPadding, 16.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                onClick = { event(NextRound) },
            )
        },
    ) { paddings ->
        PlayersList(
            modifier = Modifier.padding(paddings),
            playersTotal = state.game.calculatePlayersTotal(),
            currentRound = state.currentRound,
            editScore = { event(EditScore(it)) },
            winner = state.currentRound.winner,
            selectWinner = { event(SetWinner(it)) },
        )
    }
}

@Composable
private fun GameDialogs(state: GameViewState, event: (GameEvent) -> Unit) {
    if (state.editPlayerState != null) {
        EditDialog(
            title = stringResource(
                id = R.string.score_for_player,
                state.editPlayerState.player.name,
            ),
            textType = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            onClick = { score -> event(ConfirmEdition(score)) },
            onDismiss = { event(DismissDialog) },
        )
    }
}

@Composable
private fun PlayersList(
    modifier: Modifier = Modifier,
    playersTotal: Map<Player, Int>,
    currentRound: Round?,
    winner: PlayerId? = null,
    editScore: (Player) -> Unit,
    selectWinner: (Player) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item { GameHeaders() }
        itemsIndexed(playersTotal.toList()) { index, (player, total) ->
            PlayerItem(
                modifier = Modifier.clickable(enabled = winner != player.playerId) {
                    editScore.invoke(player)
                },
                name = player.name,
                color = index.getColorByIndex(),
            ) {
                PlayerItemStatistics(
                    total = total,
                    player = player,
                    winner = winner,
                    currentScore = currentRound?.result?.getOrDefault(player.playerId, 0),
                    selectWinner = { selectWinner(player) },
                )
            }
        }
    }
}

@Composable
private fun GameHeaders(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Spacer(modifier = Modifier.fillMaxWidth(PLAYER_ITEM_MAIN_FRACTION))
        Text(
            text = stringResource(R.string.total),
            color = AppTheme.colors.primary,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.body1,
        )
        Text(
            text = stringResource(R.string.round),
            modifier = Modifier.weight(1f),
            color = AppTheme.colors.primary,
            textAlign = TextAlign.Center,
            style = AppTheme.typography.body1,
        )
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.winner),
            color = AppTheme.colors.primary,
            textAlign = TextAlign.Center,
            style = AppTheme.typography.body1,
        )
    }
}

@Composable
private fun RowScope.PlayerItemStatistics(
    total: Int,
    currentScore: Score?,
    player: Player,
    winner: PlayerId?,
    selectWinner: () -> Unit,
) {
    Box(modifier = Modifier.weight(1f)) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = total.toString(),
            color = AppTheme.colors.onSurface,
            textAlign = TextAlign.Center,
            style = AppTheme.typography.body1,
        )
    }

    Box(
        Modifier
            .height(30.dp)
            .weight(1f)
            .padding(vertical = 0.dp, horizontal = 10.dp)
            .background(
                color = if (player.playerId == winner) {
                    AppTheme.colors.background
                } else {
                    AppTheme.colors.surface
                },
                shape = AppTheme.shapes.medium,
            ),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = currentScore.toString(),
            color = AppTheme.colors.onSurface,
            textAlign = TextAlign.Center,
            style = AppTheme.typography.body1,
        )
    }
    Box(modifier = Modifier.weight(1f)) {
        RadioButton(
            modifier = Modifier.align(Alignment.Center),
            selected = player.playerId == winner,
            onClick = selectWinner,
            colors = getAppRadioButtonColors(),
        )
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    AppTheme {
        GameScreen(state = GameViewState.STUB) {}
    }
}

@Preview
@Composable
fun PlayersListPreview() {
    AppTheme {
        Surface(
            color = AppTheme.colors.background,
        ) {
            PlayersList(
                playersTotal = Game.STUB.calculatePlayersTotal(),
                currentRound = Round.EMPTY,
                editScore = {},
                selectWinner = {},
            )
        }
    }
}
