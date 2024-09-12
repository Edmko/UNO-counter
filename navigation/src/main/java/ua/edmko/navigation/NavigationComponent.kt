package ua.edmko.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.edmko.setup.SetupRoute
import com.edmko.setup.SetupScreen
import kotlinx.coroutines.launch
import ua.edmko.core.base.NavigationManager
import ua.edmko.endgame.EndGameRoute
import ua.edmko.endgame.EndGameScreen
import ua.edmko.game.GameScreen
import ua.edmko.game.GameScreenRoute
import ua.edmko.navigation.commands.BackCommand
import ua.edmko.navigation.commands.GameSettingCommand
import ua.edmko.players.PlayersRoute
import ua.edmko.players.PlayersScreen
import ua.edmko.privacy.PolicyRoute
import ua.edmko.privacy.PolicyScreen
import ua.edmko.settings.SettingsRoute
import ua.edmko.settings.SettingsScreen

@Composable
fun NavigationComponent(
    navController: NavHostController,
    navigationManager: NavigationManager,
) {
    val coroutine = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        coroutine.launch {
            navigationManager.commands().collect { command ->
                if (command == BackCommand) {
                    navController.navigateUp()
                } else {
                    navController.navigate(command.route, command.builder)
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = GameSettingCommand.route,
    ) {
        composable<SettingsRoute> { SetupScreen() }
        composable<PlayersRoute> { PlayersScreen() }
        composable<GameScreenRoute> { GameScreen() }
        composable<SettingsRoute> { SettingsScreen() }
        composable<SetupRoute> { SetupScreen() }
        composable<EndGameRoute> { backStackEntry ->
            EndGameScreen(
                name = backStackEntry.toRoute<EndGameRoute>().name,
                back = navController::navigateUp,
            )
        }
        composable<PolicyRoute> {
            PolicyScreen(back = navController::navigateUp)
        }
    }
}
