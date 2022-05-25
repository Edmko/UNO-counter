package ua.edmko.unocounter.navigation

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edmko.setup.GameSettingScreen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ua.edmko.endgame.EndGameScreen
import ua.edmko.game.GameScreen
import ua.edmko.navigation.NavigationDirections
import ua.edmko.navigation.NavigationManager
import ua.edmko.players.PlayersScreen
import ua.edmko.privacy.PolicyScreen

const val TAG = "NAVIGATION"

@ExperimentalMaterialApi
@Composable
fun NavigationComponent(
    navController: NavHostController,
    navigationManager: NavigationManager
) {
    val coroutine = rememberCoroutineScope()
    SideEffect {
        coroutine.launch {
            navigationManager.commands().collect { command ->
                Log.d(
                    TAG,
                    "destination = ${command.destination} " +
                            "isBack = ${command == NavigationDirections.back}"
                )
                if (command == NavigationDirections.back) {
                    navController.navigateUp()
                } else {
                    navController.navigate(command.destination, command.builder)
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = NavigationDirections.gameSetting.destination
    ) {
        composable(NavigationDirections.gameSetting.destination) {
            GameSettingScreen {
                navController.navigate(
                    NavigationDirections.policy.destination,
                    NavigationDirections.policy.builder
                )
            }
        }
        composable(NavigationDirections.players.destination) {
            PlayersScreen()
        }
        composable(NavigationDirections.gameDestination) {
            GameScreen()
        }
        composable(NavigationDirections.gameEndDestination) {
            EndGameScreen(
                name = it.arguments?.getString(NavigationDirections.WINNER_NAME) ?: "",
                back = navController::navigateUp
            )
        }
        composable(NavigationDirections.lobby.destination) {
            GameSettingScreen {
                navController.navigate(
                    NavigationDirections.policy.destination,
                    NavigationDirections.policy.builder
                )
            }
        }
        composable(NavigationDirections.policy.destination) {
            PolicyScreen() {
                navController.navigateUp()
            }
        }
    }
}