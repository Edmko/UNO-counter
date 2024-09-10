package com.edmko.setup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.edmko.myapplication.R
import ua.edmko.core.extension.getColorByIndex
import ua.edmko.core.ui.components.DialogApp
import ua.edmko.core.ui.components.EditDialog
import ua.edmko.core.ui.components.GameButton
import ua.edmko.core.ui.components.PlayerItem
import ua.edmko.core.ui.components.TextFieldDivided
import ua.edmko.core.ui.components.Toolbar
import ua.edmko.core.ui.theme.AppTheme
import ua.edmko.core.ui.theme.getAppRadioButtonColors
import ua.edmko.core.ui.theme.horizontalPadding
import ua.edmko.domain.entities.GameType
import ua.edmko.domain.entities.Player

@Composable
fun SetupScreen() {
    val viewModel: SetupViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.initialize()
    }
    val state by viewModel.viewStates().collectAsState()
    state?.let { SetupContent(it, viewModel::obtainEvent) }
}

@Composable
internal fun SetupContent(
    state: SetupViewState,
    event: (GameSettingEvent) -> Unit,
) {
    Dialogs(state, event)
    Scaffold(
        modifier = Modifier,
        backgroundColor = AppTheme.colors.surface,
        topBar = { SetupToolbar(event) },
    ) { paddings ->

        Box(
            modifier = Modifier
                .padding(paddings)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                // Settings block
                val goal = state.goal.toString()
                TextFieldDivided(
                    description = stringResource(R.string.goal_title),
                    value = goal,
                    textTag = GOAL_FIELD_TAG,
                ) { event(OnGoalClickEvent) }
                TextFieldDivided(
                    description = stringResource(R.string.game_type_title),
                    value = state.gameType.name,
                    textTag = TYPE_FIELD_TAG,
                ) { event(OnTypeClickEvent) }

                // Players block
                Text(
                    style = AppTheme.typography.h5,
                    color = AppTheme.colors.onSurface,
                    text = stringResource(R.string.players_block_title),
                    modifier = Modifier.padding(horizontalPadding, 32.dp, horizontalPadding, 18.dp),
                )
                Divider(color = AppTheme.colors.onSurface)
                PlayersList(
                    modifier = Modifier
                        .weight(1f),
                    players = state.players,
                )
            }

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .navigationBarsPadding()
                    .padding(bottom = 110.dp, end = horizontalPadding),
                backgroundColor = AppTheme.colors.primary,
                onClick = { event(EditPlayers) },
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = stringResource(R.string.edit_players_content_description),
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center),
                    tint = AppTheme.colors.surface,
                )
            }

            GameButton(
                text = stringResource(R.string.start_game_button_title),
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(horizontalPadding, 0.dp, horizontalPadding, 40.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter),
                isEnabled = state.players.isNotEmpty(),
                onClick = { event(StartGame) },
            )
        }
    }
}

@Composable
private fun SetupToolbar(event: (GameSettingEvent) -> Unit) {
    Toolbar(
        modifier = Modifier.padding(end = horizontalPadding),
        title = stringResource(R.string.setup_screen_title),
        content = {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable(onClick = { event(OnSettingsClick) })
                    .testTag(SETTINGS_ICON_TAG),
                imageVector = Icons.Default.Settings,
                contentDescription = stringResource(R.string.settings_icon_content_description),
                tint = AppTheme.colors.onSurface,
            )
        },
    )
}

@Composable
private fun Dialogs(state: SetupViewState, event: (GameSettingEvent) -> Unit) {
    when (state.dialog) {
        SetupViewState.DialogType.GameType -> {
            SelectGameTypeDialog(
                title = stringResource(R.string.set_game_type_dialog_title),
                type = state.gameType,
                onDismiss = { event(SetGameType(it)) },
            )
        }

        SetupViewState.DialogType.EditGoal -> {
            EditDialog(
                testTag = EDIT_GOAL_DIALOG_TAG,
                textType = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
                title = stringResource(id = R.string.setup_goal_dialog_title),
                onDismiss = { event(DismissDialog) },
            ) { text -> event(ChangeGoal(text.toIntOrNull() ?: 0)) }
        }

        null -> {
            /** Empty */
        }
    }
}

@Composable
private fun PlayersList(
    modifier: Modifier = Modifier,
    players: List<Player>,
) {
    Box(modifier) {
        if (players.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .testTag(PLAYERS_LIST_TAG)
                    .fillMaxSize()
                    .padding(horizontalPadding, 0.dp, horizontalPadding, 0.dp),
                contentPadding = PaddingValues(bottom = 110.dp),
            ) {
                itemsIndexed(players) { index, player ->
                    val color = index.getColorByIndex()
                    PlayerItem(name = player.name, color = color)
                }
                item {
                    Spacer(
                        modifier = Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing),
                    )
                }
            }
        }
    }
}

@Composable
private fun SelectGameTypeDialog(
    title: String,
    type: GameType,
    onDismiss: (GameType) -> Unit,
) {
    var selected by remember { mutableStateOf(type) }
    DialogApp(
        title = title,
        testTag = SELECT_GAME_TYPE_DIALOG_TAG,
        onDismiss = { onDismiss(selected) },
    ) {
        Spacer(modifier = Modifier.size(15.dp))
        Row(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { selected = GameType.CLASSIC },
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = selected == GameType.CLASSIC,
                onClick = { selected = GameType.CLASSIC },
                colors = getAppRadioButtonColors(),
            )
            Text(
                text = GameType.CLASSIC.name,
                modifier = Modifier.padding(start = 10.dp),
                style = AppTheme.typography.body1,
                color = AppTheme.colors.onSurface,
            )
        }
        Row(
            Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { selected = GameType.COLLECTIVE },
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = selected == GameType.COLLECTIVE,
                onClick = { selected = GameType.COLLECTIVE },
                colors = getAppRadioButtonColors(),
            )
            Text(
                text = GameType.COLLECTIVE.name,
                modifier = Modifier.padding(start = 10.dp),
                style = AppTheme.typography.body1,
                color = AppTheme.colors.onSurface,
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
        SetupContent(state = SetupViewState.Preview, event = {})
    }
}
