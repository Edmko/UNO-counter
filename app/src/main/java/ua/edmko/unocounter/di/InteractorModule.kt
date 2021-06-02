package ua.edmko.unocounter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.edmko.unocounter.domain.interactor.*
import ua.edmko.unocounter.domain.repository.GameRepository
import ua.edmko.unocounter.domain.repository.PlayersRepository
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
class InteractorModule {

    @Singleton
    @Provides
    fun provideGetPlayersInteractor(playersRepository: PlayersRepository): ObservePlayers {
        return ObservePlayers(playersRepository)
    }

    @Singleton
    @Provides
    fun provideAddPlayersInteractor(playersRepository: PlayersRepository): AddPlayer {
        return AddPlayer(playersRepository)
    }

    @Singleton
    @Provides
    fun provideGetSelectedPlayersInteractor(playersRepository: PlayersRepository): GetSelectedPlayers {
        return GetSelectedPlayers(playersRepository)
    }

    @Singleton
    @Provides
    fun provideUpdatePlayerInteractor(playersRepository: PlayersRepository): UpdatePlayer {
        return UpdatePlayer(playersRepository)
    }

    @Singleton
    @Provides
    fun provideDeletePlayerInteractor(playersRepository: PlayersRepository): DeletePlayer {
        return DeletePlayer(playersRepository)
    }

    @Singleton
    @Provides
    fun provideGameCreateInteractir(playersRepository: PlayersRepository, gameRepository: GameRepository): CreateGame{
        return CreateGame(gameRepository, playersRepository)
    }
}