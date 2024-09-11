package ua.edmko.unocounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ua.edmko.core.base.NavigationManager
import ua.edmko.core.ui.theme.AppTheme
import ua.edmko.domain.entities.Theme
import ua.edmko.navigation.NavigationComponent
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val theme by viewModel.theme.collectAsState()
            val isDark = theme == Theme.DARK
            DisposableEffect(isDark) {
                enableEdgeToEdge(
                    SystemBarStyle.auto(
                        lightScrim = android.graphics.Color.TRANSPARENT,
                        darkScrim = android.graphics.Color.TRANSPARENT,
                    ) { isDark },
                )
                onDispose { }
            }
            AppTheme(theme) {
                Surface(
                    color = AppTheme.colors.background,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    NavigationComponent(navController, navigationManager)
                }
            }
        }
    }
}
