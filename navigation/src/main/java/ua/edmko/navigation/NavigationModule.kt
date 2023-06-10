package ua.edmko.navigation

import com.edmko.setup.SetupNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.edmko.core.base.NavigationManager
import ua.edmko.endgame.EndGameNavigator
import ua.edmko.game.GameNavigator
import ua.edmko.players.PlayersNavigator
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModule {

    @Singleton
    @Binds
    fun bindNavigationManager(impl: NavigationManagerImpl): NavigationManager

    @Binds
    fun bindPlayersNavigator(navigator: Navigator): PlayersNavigator

    @Binds
    fun bindSetupNavigator(navigator: Navigator): SetupNavigator

    @Binds
    fun bindGameNavigator(navigator: Navigator): GameNavigator

    @Binds
    fun bindEndGameNavigator(navigator: Navigator): EndGameNavigator
}
