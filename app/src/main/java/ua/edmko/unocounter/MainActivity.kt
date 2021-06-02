package ua.edmko.unocounter

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import ua.edmko.unocounter.navigation.NavigationDirections
import ua.edmko.unocounter.navigation.NavigationManager
import ua.edmko.unocounter.ui.game.GameScreen
import ua.edmko.unocounter.ui.gameSetting.GameSettingScreen
import ua.edmko.unocounter.ui.players.PlayersScreen
import ua.edmko.unocounter.ui.theme.UNOcounterTheme
import ua.edmko.unocounter.utils.LIFECYCLE
import ua.edmko.unocounter.utils.NAVIGATION
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var navigationManager: NavigationManager
    override fun onDestroy() {
        Log.d(LIFECYCLE, "OnDestroy")
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            ProvideWindowInsets {
                UNOcounterTheme {
                    navigationManager.commands.collectAsState(null).value?.also { command ->
                        Log.d(NAVIGATION, "destination = ${command.destination} isBack = ${command == NavigationDirections.back}" )
                        if (command.destination == NavigationDirections.back.destination) {
                            navController.navigateUp()
                        } else { navController.navigate(command.destination, command.builder) }
                    }
                    val viewModel: MainViewModel = viewModel()
                    Box(modifier = Modifier.fillMaxSize()) {
                        NavHost(
                            navController = navController,
                            startDestination = NavigationDirections.splash.destination
                        ) {
                            composable(NavigationDirections.splash.destination) {
                                SplashScreen()
                            }
                            composable(NavigationDirections.gameSetting.destination) {
                                GameSettingScreen(hiltViewModel())
                            }
                            composable(NavigationDirections.players.destination) {
                                PlayersScreen(hiltViewModel())
                            }
                            composable(NavigationDirections.gameDestination){
                                GameScreen(hiltViewModel(), it.arguments?.getString(NavigationDirections.GAME_ID))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = "logo",
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentScale = ContentScale.FillWidth,
        )
    }
}