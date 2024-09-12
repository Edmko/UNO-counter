package ua.edmko.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ua.edmko.core.ui.R
import ua.edmko.core.ui.theme.AppTheme
import ua.edmko.core.ui.theme.getAppRadioButtonColors

data class SelectDialogItem(
    val name: String,
    val isSelected: Boolean,
    val onClick: () -> Unit,
    val value: Any,
)

@Composable
fun SelectDialog(
    @StringRes title: Int,
    @StringRes acceptButtonTitle: Int = R.string.accept,
    items: List<SelectDialogItem>,
    onDismiss: (SelectDialogItem) -> Unit,
) {
    var currentSelection by remember { mutableStateOf(items.firstOrNull { it.isSelected }) }
    DialogApp(title = stringResource(title), onDismiss = { currentSelection?.let(onDismiss) }) {
        Spacer(modifier = Modifier.size(15.dp))
        items.forEach { item ->
            Row(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { currentSelection = item },
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = item == currentSelection,
                    onClick = { currentSelection = item },
                    colors = getAppRadioButtonColors(),
                )
                Text(
                    text = item.name,
                    modifier = Modifier.padding(start = 10.dp),
                    style = AppTheme.typography.body1,
                    color = AppTheme.colors.onSurface,
                )
            }
        }
        Text(
            text = stringResource(acceptButtonTitle),
            style = AppTheme.typography.h5,
            color = AppTheme.colors.onSurface,
            modifier = Modifier
                .padding(top = 18.dp)
                .align(Alignment.End)
                .clickable(onClick = { currentSelection?.let(onDismiss) }),
        )
    }
}
