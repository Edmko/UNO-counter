package ua.edmko.unocounter.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.edmko.data.repository.GameRepositoryImpl
import ua.edmko.data.repository.PlayersRepositoryImpl
import ua.edmko.domain.repository.GameRepository
import ua.edmko.domain.repository.PlayersRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    internal abstract fun providePlayersRepository(repository: PlayersRepositoryImpl): PlayersRepository

    @Binds
    internal abstract fun provideGameRepository(repository: GameRepositoryImpl): GameRepository
}