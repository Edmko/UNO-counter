package ua.edmko.unocounter.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.edmko.unocounter.data.repository.GameRepositoryImpl
import ua.edmko.unocounter.data.repository.PlayersRepositoryImpl
import ua.edmko.unocounter.domain.entities.Game
import ua.edmko.unocounter.domain.repository.GameRepository
import ua.edmko.unocounter.domain.repository.PlayersRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    internal abstract fun providePlayersRepository(repository: PlayersRepositoryImpl): PlayersRepository

    @Binds
    internal abstract fun provideGameRepository(repository: GameRepositoryImpl): GameRepository
}