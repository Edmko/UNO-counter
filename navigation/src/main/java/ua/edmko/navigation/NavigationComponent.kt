package ua.edmko.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edmko.setup.GameSettingScreen
import kotlinx.coroutines.launch
import ua.edmko.core.base.NavigationManager
import ua.edmko.endgame.EndGameScreen
import ua.edmko.game.GameScreen
import ua.edmko.navigation.commands.BackCommand
import ua.edmko.navigation.commands.GameCommand
import ua.edmko.navigation.commands.GameEndCommand
import ua.edmko.navigation.commands.GameSettingCommand
import ua.edmko.navigation.commands.LobbyCommand
import ua.edmko.navigation.commands.PlayersCommand
import ua.edmko.navigation.commands.PolicyCommand
import ua.edmko.players.PlayersScreen
import ua.edmko.privacy.PolicyScreen

@Composable
fun NavigationComponent(
    navController: NavHostController,
    navigationManager: NavigationManager,
) {
    val coroutine = rememberCoroutineScope()
    SideEffect {
        coroutine.launch {
            navigationManager.commands().collect { command ->
                if (command == BackCommand) {
                    navController.navigateUp()
                } else {
                    navController.navigate(command.destination, command.builder)
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = GameSettingCommand.destination,
    ) {
        composable(GameSettingCommand.destination) {
            GameSettingScreen {
                navController.navigate(
                    PolicyCommand.destination,
                    PolicyCommand.builder,
                )
            }
        }
        composable(PlayersCommand.destination) {
            PlayersScreen()
        }
        composable(GameCommand.DESTINATION) {
            GameScreen()
        }
        composable(GameEndCommand.DESTINATION) {
            EndGameScreen(
                name = it.arguments?.getString(GameEndCommand.WINNER_NAME_EXTRA).orEmpty(),
                back = navController::navigateUp,
            )
        }
        composable(LobbyCommand.destination) {
            GameSettingScreen {
                navController.navigate(
                    PolicyCommand.destination,
                    PolicyCommand.builder,
                )
            }
        }
        composable(PolicyCommand.destination) {
            PolicyScreen() {
                navController.navigateUp()
            }
        }
    }
}
