package ua.edmko.unocounter

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import ua.edmko.core.theme.UnoCounterTheme
import ua.edmko.navigation.NavigationManager
import ua.edmko.unocounter.navigation.NavigationComponent
import ua.edmko.unocounter.navigation.Navigator
import javax.inject.Inject

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {

            ProvideWindowInsets {
                UnoCounterTheme {
                    Box(modifier = Modifier.fillMaxSize()) {
                        NavigationComponent(
                            navController = rememberNavController(),
                            navigationManager = navigationManager
                        )
                    }
                }
            }
        }
    }
}