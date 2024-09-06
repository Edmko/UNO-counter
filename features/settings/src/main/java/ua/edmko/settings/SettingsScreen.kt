@file:Suppress("ktlint:standard:no-wildcard-imports")

package ua.edmko.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.Policy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ua.edmko.core.ui.components.DialogApp
import ua.edmko.core.ui.components.Toolbar
import ua.edmko.core.ui.theme.AppTheme
import ua.edmko.core.ui.theme.baseHorizontalPadding
import ua.edmko.core.ui.theme.getAppRadioButtonColors
import ua.edmko.domain.entities.Theme
import ua.edmko.settings.Dialog.*

@Composable
fun SettingsScreen(
    back: () -> Unit,
) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val state by viewModel.viewStates().collectAsState()
    state?.let {
        SettingsScreen(state = it, back = back, event = viewModel::obtainEvent)
    }
}

@Composable
internal fun SettingsScreen(
    state: SettingsViewState,
    back: () -> Unit,
    event: (SettingsEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier,
        backgroundColor = AppTheme.colors.surface,
        topBar = {
            Toolbar(
                modifier = Modifier.padding(end = baseHorizontalPadding),
                title = stringResource(R.string.settings),
            ) {
                back()
            }
        },
    ) {
        when (state.dialog) {
            Theme -> ThemeDialog(
                theme = state.theme,
            ) { event(SetTheme(it)) }

            else -> Unit
        }

        Column(modifier = Modifier.padding(it)) {
            Item(
                title = stringResource(R.string.privacy_policy),
                icon = Icons.Default.Policy,
            ) { event(PrivacyClick) }
            Item(title = "Theme: ${state.theme.name}", Icons.Default.Contrast) { event(ThemeClick) }
        }
    }
}

@Composable
internal fun Item(title: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = baseHorizontalPadding)
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
            modifier = Modifier.padding(horizontal = baseHorizontalPadding),
        )
    }
}

@Composable
private fun ThemeDialog(
    theme: Theme,
    onDismiss: (Theme) -> Unit,
) {
    var selected by remember { mutableStateOf(theme) }
    DialogApp(title = stringResource(R.string.change_theme), onDismiss = { onDismiss(theme) }) {
        Spacer(modifier = Modifier.size(15.dp))
        Row(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { selected = Theme.DARK },
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = selected == Theme.LIGHT,
                onClick = { selected = Theme.LIGHT },
                colors = getAppRadioButtonColors(),
            )
            Text(
                text = Theme.LIGHT.name,
                modifier = Modifier.padding(start = 10.dp),
                style = AppTheme.typography.body1,
                color = AppTheme.colors.onSurface,
            )
        }
        Row(
            Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { selected = Theme.DARK },
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = selected == Theme.DARK,
                onClick = { selected = Theme.DARK },
                colors = getAppRadioButtonColors(),
            )
            Text(
                text = Theme.DARK.name,
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