package ua.edmko.players

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.DismissDirection.EndToStart
import androidx.compose.material.DismissDirection.StartToEnd
import androidx.compose.material.DismissValue.Default
import androidx.compose.material.DismissValue.DismissedToEnd
import androidx.compose.material.DismissValue.DismissedToStart
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ua.edmko.core.ui.components.ConfirmationDialog
import ua.edmko.core.ui.components.EditDialog
import ua.edmko.core.ui.components.FloatingButton
import ua.edmko.core.ui.components.Toolbar
import ua.edmko.core.ui.theme.AppTheme
import ua.edmko.core.ui.theme.getCheckboxColors
import ua.edmko.domain.entities.Player
import ua.edmko.domain.entities.Player.Companion.STUB

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayersScreen() {
    val viewModel: PlayersViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.initialize()
    }
    BackHandler { viewModel.obtainEvent(NavigateBack) }
    val state by viewModel.viewStates().collectAsState()
    state?.let {
        PlayersScreen(state = it, event = viewModel::obtainEvent)
    }
}

@ExperimentalMaterialApi
@Composable
internal fun PlayersScreen(state: PlayersViewState, event: (PlayersEvent) -> Unit) {
    Dialogs(state, event)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = AppTheme.colors.background,
        topBar = { Toolbar(title = stringResource(R.string.players_screen_title)) { event(NavigateBack) } },
        floatingActionButton = {
            FloatingButton(
                icon = Icons.Filled.Add,
                modifier = Modifier.padding(bottom = 50.dp),
                onClick = { event(AddPlayerButton) },
            )
        },
    ) { paddings ->
        PLayersList(
            modifier = Modifier.padding(paddings),
            players = state.players,
            event = event,
        )
    }
}

@Composable
private fun Dialogs(state: PlayersViewState, event: (PlayersEvent) -> Unit) {
    when (state.dialog) {
        AddPlayer, is EditPlayersName -> EditDialog(
            title = stringResource(R.string.insert_name),
            onDismiss = { event(DismissDialog) },
            onClick = { text ->
                when (state.dialog) {
                    AddPlayer -> event(CreatePlayer(text))
                    is EditPlayersName -> event(ChangePlayersName(state.dialog.player, text))
                    else -> Unit
                }
            },
        )

        is DeletePlayer -> ConfirmationDialog(
            title = stringResource(R.string.are_delete_player),
            dismiss = { event(DismissDialog) },
            accept = { event(DeletePlayerEvent(state.dialog.player)) },
        )

        null -> Unit
    }
}

@ExperimentalMaterialApi
@Composable
private fun PLayersList(
    modifier: Modifier = Modifier,
    players: List<Player>,
    event: (PlayersEvent) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding(),
    ) {
        item { PlayerDivider() }
        items(
            items = players,
            key = { item -> item.playerId },
        ) { player ->
            val dismissState = rememberDismissState { dismissState ->
                when (dismissState) {
                    DismissedToEnd -> event(OnDeletePlayer(player))
                    DismissedToStart -> event(EditPlayer(player))
                    Default -> Unit
                }
                dismissState == Default
            }
            SwipeToDismiss(
                state = dismissState,
                background = {
                    val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
                    val icon = when (direction) {
                        StartToEnd -> Icons.Default.Delete
                        EndToStart -> Icons.Default.Edit
                    }
                    val color by animateColorAsState(
                        targetValue = when (dismissState.targetValue) {
                            Default -> Color.White
                            DismissedToEnd -> Color.Red
                            DismissedToStart -> Color.Gray
                        },
                        label = "",
                    )
                    val scale by animateFloatAsState(
                        targetValue = if (dismissState.targetValue == Default) 0.75f else 1f,
                        label = "",
                    )

                    val alignment = when (direction) {
                        StartToEnd -> Alignment.CenterStart
                        EndToStart -> Alignment.CenterEnd
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color),
                        contentAlignment = alignment,
                    ) {
                        Icon(
                            icon,
                            contentDescription = null,
                            modifier = Modifier
                                .scale(scale)
                                .size(60.dp)
                                .padding(10.dp),
                        )
                    }
                },
                dismissContent = { PlayerItem(player = player, event = event) },
            )
            PlayerDivider()
        }
    }
}

@Composable
private fun PlayerDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier
            .height(1.dp)
            .fillMaxWidth(),
        color = AppTheme.colors.onSurface,
    )
}

@Composable
private fun PlayerItem(
    modifier: Modifier = Modifier,
    player: Player,
    event: (PlayersEvent) -> Unit,
) {
    val textColor by animateColorAsState(
        targetValue = if (player.isSelected) {
            AppTheme.colors.secondary
        } else {
            AppTheme.colors.onSurface
        },
        label = "",
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.colors.background)
            .height(50.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = player.isSelected,
                onCheckedChange = { isChecked ->
                    event(UpdatePlayersSelection(player.copy(isSelected = isChecked)))
                },
                modifier = Modifier.padding(start = 18.dp),
                colors = getCheckboxColors(),
            )
            Text(
                text = player.name,
                color = textColor,
                style = AppTheme.typography.h5,
                modifier = Modifier.padding(start = 10.dp),
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PlayerListPreview() {
    AppTheme {
        PLayersList(
            players = STUB,
            event = {},
        )
    }
}

@Preview
@Composable
fun PlayerItemPreview() {
    AppTheme {
        PlayerItem(player = STUB.first()) {}
    }
}

@Preview
@Composable
fun ConfirmationDialogPreview() {
    AppTheme {
        ConfirmationDialog(
            title = "Title",
            dismiss = { /** Empty */ },
            accept = { /** Empty */ },
        )
    }
}
