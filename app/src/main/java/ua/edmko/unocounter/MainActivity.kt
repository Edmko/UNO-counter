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
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ua.edmko.unocounter.navigation.NavigationDirections
import ua.edmko.unocounter.navigation.NavigationManager
import ua.edmko.unocounter.ui.gameSetting.GameSettingScreen
import ua.edmko.unocounter.ui.players.PlayersScreen
import ua.edmko.unocounter.ui.theme.UNOcounterTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var navigationManager: NavigationManager
    override fun onDestroy() {
        Log.d(this.javaClass.simpleName, "OnDestroy")
        super.onDestroy()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            UNOcounterTheme {
                navigationManager.commands.collectAsState(initial = NavigationDirections.default).value.also { command ->
                    Log.d(this.javaClass.simpleName, "destination = ${command.destination}")
                    if (command.destination.isNotEmpty()) navController.navigate(
                        command.destination,
                        command.builder
                    )
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
                            GameSettingScreen(
                                navController.hiltNavGraphViewModel(NavigationDirections.gameSetting.destination)
                            )
                        }
                        composable(NavigationDirections.players.destination) {
                            PlayersScreen(navController.hiltNavGraphViewModel(NavigationDirections.players.destination))
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