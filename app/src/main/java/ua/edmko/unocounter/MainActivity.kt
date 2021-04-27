package ua.edmko.unocounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import dagger.hilt.android.AndroidEntryPoint
import ua.edmko.unocounter.navigation.NavigationDirections
import ua.edmko.unocounter.navigation.NavigationManager
import ua.edmko.unocounter.ui.gameSetting.GameSettingScreen
import ua.edmko.unocounter.ui.theme.UNOcounterTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            navigationManager.commands.collectAsState().value.also { command ->
                if (command.destination.isNotEmpty()) navController.navigate(command.destination, command.builder)
            }

            UNOcounterTheme {
                MainScreen(navController)
            }
        }
    }
}

@Composable
fun MainScreen(
    navController: NavHostController
) {
    val viewModel: MainViewModel = viewModel()
    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = NavigationDirections.splashRoot.destination
        ) {
            navigation(
                startDestination = NavigationDirections.splash.destination,
                route = NavigationDirections.splashRoot.destination
            ) {
                composable(NavigationDirections.splash.destination) {
                    SplashScreen()
                }
            }
            navigation(
                startDestination = NavigationDirections.gameSetting.destination,
                route = NavigationDirections.homeRoot.destination
            ) {
                composable(NavigationDirections.gameSetting.destination) {
                    GameSettingScreen(
                        navController.hiltNavGraphViewModel(NavigationDirections.gameSetting.destination)
                    )
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