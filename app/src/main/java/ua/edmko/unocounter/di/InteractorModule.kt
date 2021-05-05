package ua.edmko.unocounter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.edmko.unocounter.domain.interactor.AddPlayer
import ua.edmko.unocounter.domain.interactor.DeletePlayer
import ua.edmko.unocounter.domain.interactor.GetPlayers
import ua.edmko.unocounter.domain.interactor.UpdatePlayer
import ua.edmko.unocounter.domain.repository.PlayersRepository
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
class InteractorModule {

    @Singleton
    @Provides
    fun provideGetPlayersInteractor(playersRepository: PlayersRepository): GetPlayers {
        return GetPlayers(playersRepository)
    }

    @Singleton
    @Provides
    fun provideAddPlayersInteractor(playersRepository: PlayersRepository): AddPlayer {
        return AddPlayer(playersRepository)
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
}