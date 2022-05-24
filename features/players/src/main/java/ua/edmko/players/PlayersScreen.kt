package ua.edmko.players

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.DismissDirection.*
import androidx.compose.material.DismissValue.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ua.edmko.components.ConfirmationDialog
import ua.edmko.components.EditDialog
import ua.edmko.components.Toolbar
import ua.edmko.theme.AppTheme
import ua.edmko.theme.baseDp
import ua.edmko.domain.entities.Player
import ua.edmko.domain.entities.Player.Companion.getPlayersStub

@ExperimentalMaterialApi
@Composable
fun PlayersScreen(viewModel: PlayersViewModel = hiltViewModel()) {
    val state by viewModel.viewStates().collectAsState()
    Scaffold(
        topBar = { Toolbar(title = "Players") { viewModel.obtainEvent(NavigateBack) } },
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
            if (state?.editDialogShows == true) EditDialog(
                title = stringResource(R.string.insert_name),
                onDismiss = { viewModel.obtainEvent(DismissDialog) },
                onClick = { text ->
                    if (state?.selectedPlayer == null) {
                        viewModel.obtainEvent(CreatePlayer(text))
                    } else {
                        viewModel.obtainEvent(ChangePlayersName(text))
                    }
                })
            if (state?.confirmationDialogShows == true) ConfirmationDialog(
                title = stringResource(R.string.are_delete_player),
                dismiss = { viewModel.obtainEvent(DismissDialog) },
                accept = { viewModel.obtainEvent(DeletePlayerEvent) }
            )
            state?.players?.let { players -> PLayersList(players, viewModel::obtainEvent) }

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 50.dp, end = baseDp),
                backgroundColor = Color.Red,
                onClick = { viewModel.obtainEvent(AddPlayerButton) }) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Edit",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center),
                    tint = Color.Black
                )

            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun PLayersList(players: List<Player>, event: (PlayersEvent) -> Unit) {
    LazyColumn() {
        items(players) { player ->
            key(player.playerId) {
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
                            }
                        )
                        val scale by animateFloatAsState(
                            if (dismissState.targetValue == Default) 0.75f else 1f
                        )

                        val alignment = when (direction) {
                            StartToEnd -> Alignment.CenterStart
                            EndToStart -> Alignment.CenterEnd
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color),
                            contentAlignment = alignment
                        ) {
                            Icon(
                                icon,
                                contentDescription = "Delete by swipe left end edit by swipe right",
                                modifier = Modifier
                                    .scale(scale)
                                    .size(60.dp)
                                    .padding(10.dp)
                            )
                        }
                    },
                    dismissContent = { PlayerItem(player, event) }
                )
            }
        }

    }
}


@Composable
fun PlayerItem(player: Player, event: (PlayersEvent) -> Unit) {
    val textColor by animateColorAsState(
        if (player.isSelected) {
            AppTheme.colors.secondary
        } else {
            AppTheme.colors.onBackground
        }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.background)
            .height(50.dp)
    ) {
        Checkbox(
            checked = player.isSelected,
            onCheckedChange = { isChecked ->
                event(UpdatePlayersSelection(player.copy(isSelected = isChecked)))
            },
            modifier = Modifier
                .padding(start = 18.dp)
                .align(Alignment.CenterStart)
        )
        Text(
            text = player.name,
            color = textColor,
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 54.dp)
        )
        Divider(
            Modifier
                .height(1.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            color = AppTheme.colors.onBackground
        )
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PlayerListPreview() {
    AppTheme() {
        PLayersList(
            players = getPlayersStub(),
            event = {}
        )
    }
}

@Preview
@Composable
fun PlayerItemPreview(name: String = "John Simons") {
    AppTheme {
        PlayerItem(player = getPlayersStub().first()) {}
    }
}