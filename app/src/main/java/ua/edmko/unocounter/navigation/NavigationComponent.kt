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
            GameSettingScreen(hiltViewModel())
        }
        composable(NavigationDirections.players.destination) {
            PlayersScreen(hiltViewModel())
        }
        composable(NavigationDirections.gameDestination) {
            GameScreen(hiltViewModel())
        }
        composable(NavigationDirections.gameEndDestination) {
            EndGameScreen(
                hiltViewModel(),
                it.arguments?.getString(NavigationDirections.WINNER_NAME) ?: ""
            )
        }
        composable(NavigationDirections.lobby.destination) {
            GameSettingScreen(hiltViewModel())
        }
    }
}