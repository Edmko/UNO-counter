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
import androidx.hilt.navigation.compose.hiltViewModel
import com.edmko.myapplication.R
import ua.edmko.core.ui.components.*
import ua.edmko.core.extension.getColorByIndex
import ua.edmko.core.ui.theme.AppTheme
import ua.edmko.core.ui.theme.baseHorizontalPadding
import ua.edmko.core.ui.theme.getAppRadioButtonColors
import ua.edmko.domain.entities.GameType
import ua.edmko.domain.entities.Player

@Composable
fun GameSettingScreen(viewModel: SetupViewModel = hiltViewModel()) {
    val state by viewModel.viewStates().collectAsState()
    state?.let { GameSettingContent(it, viewModel::obtainEvent) }
}

@Composable
internal fun GameSettingContent(state: SetupViewState, event: (GameSettingEvent) -> Unit) {
    Surface(
        modifier = Modifier,
        color = AppTheme.colors.surface
    ) {
        //dialog
        when (state.dialog) {
            SetupViewState.DialogType.Type -> {
                OptionsDialog(
                    title = stringResource(R.string.choose_type),
                    type = state.gameType,
                ) {
                    event(SetGameType(it))
                }
            }
            SetupViewState.DialogType.Edit -> {
                EditDialog(
                    textType = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    title = stringResource(id = R.string.insert_goal),
                    onDismiss = { event(DismissDialog) }
                ) { text -> event(ChangeGoal(text.toIntOrNull() ?: 0)) }
            }
            null -> {
                /** Empty */
            }
        }
        //content
        Box(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            Column(
                Modifier.fillMaxSize()
            ) {
                val goal = state.goal.toString()
                TextFieldDivided(
                    modifier = Modifier,
                    stringResource(id = R.string.goal),
                    goal
                ) { event(OnGoalClickEvent) }
                TextFieldDivided(
                    modifier = Modifier,
                    stringResource(R.string.type),
                    state.gameType.name
                ) { event(OnTypeClickEvent) }
                Text(
                    style = AppTheme.typography.h5,
                    color = AppTheme.colors.onSurface,
                    text = stringResource(R.string.players),
                    modifier = Modifier.padding(18.dp, 32.dp, 18.dp, 18.dp)
                )
                Divider(color = AppTheme.colors.onSurface)
                PlayersList(
                    modifier = Modifier
                        .weight(1f),
                    players = state.players
                )
            }

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .navigationBarsPadding()
                    .padding(bottom = 110.dp, end = baseHorizontalPadding),
                backgroundColor = AppTheme.colors.primary,
                onClick = { event(EditPlayers) }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit players",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center),
                    tint = AppTheme.colors.surface
                )

            }

            GameButton(
                text = stringResource(R.string.start_game),
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(18.dp, 0.dp, 18.dp, 40.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter),
                isEnabled = state.players.isNotEmpty(),
                onClick = { event(StartGame) })
        }
    }
}

@Composable
fun PlayersList(modifier: Modifier = Modifier, players: List<Player>) {
    Box(modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp, 0.dp, 18.dp, 0.dp),
            contentPadding = PaddingValues(bottom = 110.dp)
        ) {
            itemsIndexed(players) { index, player ->
                val color = index.getColorByIndex()
                PlayerItem(name = player.name, color = color)
            }
            item {
                Spacer(
                    Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing)
                )
            }
        }
    }
}

@Composable
fun OptionsDialog(
    title: String,
    type: GameType,
    onDismiss: (GameType) -> Unit
) {
    var selected by remember { mutableStateOf(type) }
    DialogApp(title = title, onDismiss = { onDismiss(selected) }) {
        Spacer(modifier = Modifier.size(15.dp))
        Row(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { selected = GameType.CLASSIC }
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = selected == GameType.CLASSIC,
                onClick = { selected = GameType.CLASSIC },
                colors = getAppRadioButtonColors()
            )
            Text(
                text = GameType.CLASSIC.name,
                modifier = Modifier.padding(start = 10.dp),
                style = AppTheme.typography.body1,
                color = AppTheme.colors.onSurface
            )
        }
        Row(
            Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { selected = GameType.COLLECTIVE }
            ),
            verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selected == GameType.COLLECTIVE,
                onClick = { selected = GameType.COLLECTIVE },
                colors = getAppRadioButtonColors()
            )
            Text(
                text = GameType.COLLECTIVE.name,
                modifier = Modifier.padding(start = 10.dp),
                style = AppTheme.typography.body1,
                color = AppTheme.colors.onSurface
            )
        }
        Text(
            text = stringResource(ua.edmko.core.R.string.accept),
            style = AppTheme.typography.h5,
            color = AppTheme.colors.onSurface,
            modifier = Modifier
                .padding(top = 18.dp)
                .align(Alignment.End)
                .clickable(onClick = { onDismiss(selected) }),
        )

    }
}

@Preview
@Composable
fun GameSettingContentPreview() {
    AppTheme {
        GameSettingContent(state = SetupViewState.Preview, event = {})
    }
}



