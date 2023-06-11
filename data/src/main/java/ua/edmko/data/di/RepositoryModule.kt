package ua.edmko.data.di

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
internal abstract class RepositoryModule {

    @Binds
    abstract fun providePlayersRepository(repository: PlayersRepositoryImpl): PlayersRepository

    @Binds
    abstract fun provideGameRepository(repository: GameRepositoryImpl): GameRepository
}
