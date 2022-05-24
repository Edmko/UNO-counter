package com.edmko.setup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.edmko.myapplication.R
import ua.edmko.components.*
import ua.edmko.core.extension.getColorByIndex
import ua.edmko.theme.AppTheme
import ua.edmko.theme.baseDp
import ua.edmko.domain.entities.GameType
import ua.edmko.domain.entities.Player
import ua.edmko.domain.entities.Player.Companion.getPlayersStub

@Composable
fun GameSettingScreen(viewModel: SetupViewModel = hiltViewModel()) {
    val state by viewModel.viewStates().collectAsState()
    GameSettingContent(state, viewModel::obtainEvent)
}

@Composable
internal fun GameSettingContent(state: SetupViewState?, event: (GameSettingEvent) -> Unit) {
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
        if (state?.typeDialogShows == true) OptionsDialog(
            title = stringResource(R.string.choose_type),
            type = state.gameType
        ) {
            event(SetGameType(it))
        }
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
                    state?.gameType?.name ?: ""
                ) { event(OnTypeClickEvent) }
                Text(
                    style = AppTheme.typography.h6,
                    color = AppTheme.colors.onBackground,
                    text = stringResource(R.string.players),
                    modifier = Modifier.padding(18.dp, 32.dp, 18.dp, 18.dp)
                )
                Divider(color = AppTheme.colors.onBackground)
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
                    tint = AppTheme.colors.background
                )

            }

            GameButton(
                text = stringResource(R.string.start_game),
                modifier = Modifier
                    .padding(18.dp, 0.dp, 18.dp, 40.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter),
                isEnabled = state?.players?.isNotEmpty() ?: false,
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
                .padding(18.dp, 0.dp, 18.dp, 0.dp),
            contentPadding = PaddingValues(bottom = 110.dp)
        ) {
            players?.let {
                itemsIndexed(it) { index, player ->
                    val color = index.getColorByIndex()
                    PlayerItem(name = player.name, color = color)
                }
            }
        }
    }
}

@Composable
fun OptionsDialog(
    title: String,
    type: GameType,
    dismiss: (GameType) -> Unit
) {
    var selected by remember { mutableStateOf(type) }
    DialogApp(title = title, onDismiss = { dismiss(selected) }) {


        Spacer(modifier = Modifier.size(15.dp))
        Row(Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { selected = GameType.CLASSIC }) {
            RadioButton(selected = selected == GameType.CLASSIC, onClick = {
                selected = GameType.CLASSIC
            })
            Text(
                text = GameType.CLASSIC.name,
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.size(15.dp))
        Row(
            Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { selected = GameType.COLLECTIVE }) {
            RadioButton(
                selected = selected == GameType.COLLECTIVE,
                onClick = { selected = GameType.COLLECTIVE })
            Text(
                text = GameType.COLLECTIVE.name,
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 18.sp
            )
        }
        Text(
            text = stringResource(ua.edmko.core.R.string.accept),
            color = MaterialTheme.colors.onSurface,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 18.dp)
                .align(Alignment.End)
                .clickable(onClick = { dismiss(selected) }),
        )
    }
}

@Preview
@Composable
fun GameSettingContentPreview() {
    AppTheme {
        GameSettingContent(state = SetupViewState(players = getPlayersStub())) {
        }
    }
}



