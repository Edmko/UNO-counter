package com.edmko.setup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import ua.edmko.core.ui.components.EditDialog
import ua.edmko.core.ui.components.FloatingButton
import ua.edmko.core.ui.components.GameButton
import ua.edmko.core.ui.components.PlayerItem
import ua.edmko.core.ui.components.SelectDialog
import ua.edmko.core.ui.components.SelectDialogItem
import ua.edmko.core.ui.components.TextFieldDivided
import ua.edmko.core.ui.components.Toolbar
import ua.edmko.core.ui.extension.getColorByIndex
import ua.edmko.core.ui.theme.AppTheme
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
        backgroundColor = AppTheme.colors.background,
        topBar = { SetupToolbar { event(OnSettingsClick) } },
        floatingActionButton = {
            FloatingButton(
                icon = Icons.Default.Edit,
                onClick = { event(EditPlayers) },
            )
        },
        bottomBar = {
            GameButton(
                text = stringResource(R.string.start_game_button_title),
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(start = horizontalPadding, end = horizontalPadding, bottom = 16.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                isEnabled = state.players.isNotEmpty(),
                onClick = { event(StartGame) },
            )
        },
    ) { paddings ->
        Column(
            modifier = Modifier
                .padding(paddings)
                .fillMaxSize(),
        ) {
            TextFieldDivided(
                description = stringResource(R.string.goal_title),
                value = state.goal.toString(),
                textTag = GOAL_FIELD_TAG,
            ) { event(OnGoalClickEvent) }

            TextFieldDivided(
                description = stringResource(R.string.game_type_title),
                value = state.gameType.name,
                textTag = TYPE_FIELD_TAG,
            ) { event(OnTypeClickEvent) }

            Text(
                style = AppTheme.typography.h5,
                color = AppTheme.colors.onSurface,
                text = stringResource(R.string.players_block_title),
                modifier = Modifier.padding(horizontalPadding, 32.dp, horizontalPadding, 18.dp),
            )

            Divider(color = AppTheme.colors.onSurface)

            PlayersList(
                modifier = Modifier.weight(1f),
                players = state.players,
            )
        }
    }
}

@Composable
private fun SetupToolbar(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit,
) {
    Toolbar(
        title = stringResource(R.string.setup_screen_title),
        content = {
            Icon(
                modifier = modifier
                    .align(Alignment.CenterVertically)
                    .clickable(onClick = onSettingsClick)
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
            SelectDialog(
                title = R.string.set_game_type_dialog_title,
                items = GameType.entries.map {
                    SelectDialogItem(
                        name = it.name,
                        isSelected = it == state.gameType,
                        value = state.dialog,
                        onClick = { SetGameType(it) },
                    )
                },
                onDismiss = {
                    event(SetGameType(it.value as GameType))
                },
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
    if (players.isNotEmpty()) {
        LazyColumn(
            modifier = modifier
                .testTag(PLAYERS_LIST_TAG)
                .padding(horizontalPadding, 0.dp, horizontalPadding, 0.dp),
            contentPadding = PaddingValues(bottom = 32.dp),
        ) {
            itemsIndexed(players) { index, player ->
                val color = index.getColorByIndex()
                PlayerItem(name = player.name, color = color)
            }
        }
    }
}

@Preview
@Composable
fun GameSettingContentPreview() {
    AppTheme {
        SetupContent(state = SetupViewState.Preview, event = {})
    }
}
