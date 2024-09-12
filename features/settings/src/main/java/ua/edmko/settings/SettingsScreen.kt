package ua.edmko.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.Policy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ua.edmko.core.ui.components.SelectDialog
import ua.edmko.core.ui.components.SelectDialogItem
import ua.edmko.core.ui.components.Toolbar
import ua.edmko.core.ui.theme.AppTheme
import ua.edmko.core.ui.theme.horizontalPadding
import ua.edmko.domain.entities.Theme

@Composable
fun SettingsScreen() {
    val viewModel: SettingsViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.initialize()
    }
    BackHandler { viewModel.obtainEvent(NavigateBack) }
    val state by viewModel.viewStates().collectAsState()
    state?.let {
        SettingsScreen(state = it, event = viewModel::obtainEvent)
    }
}

@Composable
internal fun SettingsScreen(
    state: SettingsViewState,
    event: (SettingsEvent) -> Unit,
) {
    Dialogs(state, event)
    Scaffold(
        modifier = Modifier,
        backgroundColor = AppTheme.colors.background,
        topBar = {
            Toolbar(
                title = stringResource(R.string.settings_screen_title),
                back = { event(NavigateBack) },
            )
        },
    ) { paddings ->
        Column(modifier = Modifier.padding(paddings)) {
            Item(
                title = stringResource(R.string.item_privacy_policy_title),
                icon = Icons.Default.Policy,
                onClick = { event(PrivacyClick) },
            )
            Item(
                title = stringResource(R.string.item_theme_title, state.theme.name),
                icon = Icons.Default.Contrast,
                onClick = { event(ThemeClick) },
            )
        }
    }
}

@Composable
private fun Dialogs(state: SettingsViewState, event: (SettingsEvent) -> Unit) {
    when (state.dialog) {
        Dialog.Theme -> ThemeDialog(
            theme = state.theme,
            onDismiss = { event(SetThemeEvent(it)) },
        )

        null -> Unit
    }
}

@Composable
internal fun Item(title: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding)
            .height(54.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier,
            imageVector = icon,
            contentDescription = title,
            tint = AppTheme.colors.onSurface,
        )
        Text(
            style = AppTheme.typography.body1,
            color = AppTheme.colors.onSurface,
            text = title,
            modifier = Modifier.padding(horizontal = horizontalPadding),
        )
    }
}

@Composable
private fun ThemeDialog(
    theme: Theme,
    onDismiss: (Theme) -> Unit,
) {
    SelectDialog(
        title = R.string.change_theme_dialog_title,
        items = Theme.entries.map {
            SelectDialogItem(
                name = it.name,
                onClick = { onDismiss(it) },
                isSelected = theme == it,
                value = it,
            )
        },
        onDismiss = { onDismiss(it.value as Theme) },
    )
}

@Preview
@Composable
fun SettingsScreenPreview() {
    AppTheme {
        SettingsScreen(state = SettingsViewState(Theme.LIGHT)) {
        }
    }
}
