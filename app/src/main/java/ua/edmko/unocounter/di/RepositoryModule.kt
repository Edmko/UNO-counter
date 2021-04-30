package ua.edmko.unocounter.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.edmko.unocounter.data.repository.PlayersRepositoryImpl
import ua.edmko.unocounter.domain.repository.PlayersRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    internal abstract fun providePlayersRepository(repository: PlayersRepositoryImpl): PlayersRepository
}