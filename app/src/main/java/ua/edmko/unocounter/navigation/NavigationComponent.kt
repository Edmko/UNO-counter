package ua.edmko.unocounter.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.coroutines.flow.onEach
import ua.edmko.navigation.NavigationDirections
import ua.edmko.navigation.NavigationManager
import ua.edmko.unocounter.SplashScreen
import ua.edmko.unocounter.ui.components.EndGameScreen
import ua.edmko.unocounter.ui.game.GameScreen
import ua.edmko.unocounter.ui.gameSetting.GameSettingScreen
import ua.edmko.unocounter.ui.players.PlayersScreen
import ua.edmko.unocounter.utils.NAVIGATION

@Composable
fun NavigationComponent(
    navController: NavHostController,
    navigationManager: NavigationManager
) {
    LaunchedEffect("navigation") {
        navigationManager.commands.onEach {
            it?.let { command ->
                Log.d(
                    NAVIGATION,
                    "destination = ${command.destination} " +
                            "isBack = ${command == NavigationDirections.back}"
                )
                if (command.destination == NavigationDirections.back.destination) {
                    navController.navigateUp()
                } else {
                    navController.navigate(command.destination, command.builder)
                }
            }
        }
    }
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
        composable(NavigationDirections.gameDestination) {
            GameScreen(
                hiltViewModel(),
                it.arguments?.getString(NavigationDirections.GAME_ID)
                    ?: throw IllegalArgumentException("Game id must not be null")
            )
        }
        composable(NavigationDirections.gameEndDestination) {
            EndGameScreen(
                hiltViewModel(),
                it.arguments?.getString(NavigationDirections.PLAYER_NAME) ?: ""
            )
        }
        composable(NavigationDirections.lobby.destination) {
            GameSettingScreen(hiltViewModel())
        }
    }
}