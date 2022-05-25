package ua.edmko.endgame

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.endgame.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.edmko.core.ui.theme.AppTheme

@Composable
fun EndGameScreen(name: String, back: () -> Unit) {
    AppTheme {
        Surface(
            color = AppTheme.colors.surface
        ) {
            Box(Modifier.fillMaxSize()) {
                Column(Modifier.align(Alignment.Center)) {
                    Text(
                        text = stringResource(R.string.congratulation),
                        style = AppTheme.typography.h4,
                        color = AppTheme.colors.onSurface
                    )
                    Text(
                        text = name,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .align(Alignment.CenterHorizontally),
                        style = AppTheme.typography.h4,
                        color = AppTheme.colors.primary
                    )
                }
            }
        }
    }
    val scope = rememberCoroutineScope()
    SideEffect {
        scope.launch {
            delay(3000)
            back()
        }
    }

}