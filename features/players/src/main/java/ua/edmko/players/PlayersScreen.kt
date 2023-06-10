package ua.edmko.players

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Checkbox
import androidx.compose.material.DismissDirection.EndToStart
import androidx.compose.material.DismissDirection.StartToEnd
import androidx.compose.material.DismissValue.Default
import androidx.compose.material.DismissValue.DismissedToEnd
import androidx.compose.material.DismissValue.DismissedToStart
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
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
import ua.edmko.core.ui.components.Toolbar
import ua.edmko.core.ui.theme.AppTheme
import ua.edmko.core.ui.theme.baseHorizontalPadding
import ua.edmko.core.ui.theme.getCheckboxColors
import ua.edmko.domain.entities.Player
import ua.edmko.domain.entities.Player.Companion.playersStubList

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayersScreen(viewModel: PlayersViewModel = hiltViewModel()) {
    val state by viewModel.viewStates().collectAsState()
    state?.let {
        PlayersScreen(state = it, event = viewModel::obtainEvent)
    }
}

@ExperimentalMaterialApi
@Composable
internal fun PlayersScreen(state: PlayersViewState, event: (PlayersEvent) -> Unit) {
    Scaffold(
        topBar = {
            Toolbar(title = "Players") {
                event(
                    NavigateBack,
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = AppTheme.colors.surface,
    ) { paddings ->
        Box(
            Modifier
                .padding(paddings)
                .fillMaxSize(),
        ) {
            if (state.editDialogShows) {
                EditDialog(
                    title = stringResource(R.string.insert_name),
                    onDismiss = { event(DismissDialog) },
                    onClick = { text ->
                        if (state.selectedPlayer == null) {
                            event(CreatePlayer(text))
                        } else {
                            event(ChangePlayersName(text))
                        }
                    },
                )
            }
            if (state.confirmationDialogShows) {
                ConfirmationDialog(
                    title = stringResource(R.string.are_delete_player),
                    dismiss = { event(DismissDialog) },
                    accept = { event(DeletePlayerEvent) },
                )
            }
            PLayersList(
                players = state.players,
                event = event,
            )

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 50.dp, end = baseHorizontalPadding),
                backgroundColor = AppTheme.colors.primary,
                onClick = { event(AddPlayerButton) },
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Edit",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center),
                    tint = AppTheme.colors.onPrimary,
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun PLayersList(
    players: List<Player>,
    event: (PlayersEvent) -> Unit,
) {
    LazyColumn() {
        itemsIndexed(players) { index, player ->
            key(player.playerId) {
                if (index == 0) {
                    Divider(
                        Modifier
                            .height(1.dp)
                            .fillMaxWidth(),
                        color = AppTheme.colors.onSurface,
                    )
                }
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
                        val direction =
                            dismissState.dismissDirection ?: return@SwipeToDismiss
                        val icon = when (direction) {
                            StartToEnd -> Icons.Default.Delete
                            EndToStart -> Icons.Default.Edit
                        }
                        val color by animateColorAsState(
                            when (dismissState.targetValue) {
                                Default -> Color.White
                                DismissedToEnd -> Color.Red
                                DismissedToStart -> Color.Gray
                            },
                        )
                        val scale by animateFloatAsState(
                            if (dismissState.targetValue == Default) 0.75f else 1f,
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
                                contentDescription = "Delete by swipe left end edit by swipe right",
                                modifier = Modifier
                                    .scale(scale)
                                    .size(60.dp)
                                    .padding(10.dp),
                            )
                        }
                    },
                    dismissContent = { PlayerItem(player, event) },
                )
            }
            Divider(
                Modifier
                    .height(1.dp)
                    .fillMaxWidth(),
                color = AppTheme.colors.onSurface,
            )
        }
        item {
            Spacer(
                Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing),
            )
        }
    }
}

@Composable
fun PlayerItem(player: Player, event: (PlayersEvent) -> Unit) {
    val textColor by animateColorAsState(
        if (player.isSelected) {
            AppTheme.colors.secondary
        } else {
            AppTheme.colors.onSurface
        },
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.surface)
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
                modifier = Modifier
                    .padding(start = 10.dp),
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PlayerListPreview() {
    AppTheme() {
        PLayersList(
            players = playersStubList,
            event = {},
        )
    }
}

@Preview
@Composable
fun PlayerItemPreview() {
    AppTheme {
        PlayerItem(player = playersStubList.first()) {}
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
