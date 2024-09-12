package ua.edmko.core.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ua.edmko.core.ui.theme.AppTheme

@Composable
fun FloatingButton(modifier: Modifier = Modifier, icon: ImageVector, onClick: () -> Unit) {
    FloatingActionButton(
        modifier = modifier,
        backgroundColor = AppTheme.colors.primary,
        onClick = onClick,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(30.dp),
            tint = AppTheme.colors.onPrimary,
        )
    }
}
