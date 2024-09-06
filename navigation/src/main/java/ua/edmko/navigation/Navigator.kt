package ua.edmko.navigation

import com.edmko.setup.SetupNavigator
import ua.edmko.core.base.NavigationManager
import ua.edmko.endgame.EndGameNavigator
import ua.edmko.game.GameNavigator
import ua.edmko.navigation.commands.GameCommand
import ua.edmko.navigation.commands.GameEndCommand
import ua.edmko.navigation.commands.PlayersCommand
import ua.edmko.navigation.commands.PolicyCommand
import ua.edmko.players.PlayersNavigator
import ua.edmko.settings.SettingsNavigator
import javax.inject.Inject

internal class Navigator @Inject constructor(
    private val navigationManager: NavigationManager,
) : SetupNavigator, PlayersNavigator, EndGameNavigator, GameNavigator, SettingsNavigator {

    override fun back() {
        navigationManager.back()
    }

    override fun toPlayers() {
        navigationManager.navigate(PlayersCommand)
    }

    override fun toGame(gameId: String) {
        navigationManager.navigate(GameCommand(gameId))
    }

    override fun toEnd(winner: String) {
        navigationManager.navigate(GameEndCommand(winner))
    }

    override fun toPolicy() {
        navigationManager.navigate(PolicyCommand)
    }
}
