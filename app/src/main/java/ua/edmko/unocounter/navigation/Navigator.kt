package ua.edmko.unocounter.navigation

import com.edmko.setup.SetupNavigator
import ua.edmko.endgame.EndGameNavigator
import ua.edmko.game.GameNavigator
import ua.edmko.navigation.NavigationDirections
import ua.edmko.navigation.NavigationManager
import ua.edmko.players.PlayersNavigator
import javax.inject.Inject

class Navigator @Inject constructor(
    private val navigationManager: NavigationManager
) : SetupNavigator, PlayersNavigator, EndGameNavigator, GameNavigator {

    companion object {
        const val TAG = "NAVIGATOR"
    }

    override fun back() {
       navigationManager.back()
    }

    override fun toPlayers() {
        navigationManager.navigate(NavigationDirections.players)
    }

    override fun toGame(id: String) {
        navigationManager.navigate(NavigationDirections.game(id))
    }

    override fun toEnd(winner: String) {
        navigationManager.navigate(NavigationDirections.gameEnd(winner))
    }
}