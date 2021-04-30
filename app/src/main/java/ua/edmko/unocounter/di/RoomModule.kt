package ua.edmko.unocounter.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.edmko.unocounter.data.local.AppDatabase
import ua.edmko.unocounter.data.local.PlayersDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase(context)
    }

    @Singleton
    @Provides
    fun providePlayersDao(database: AppDatabase): PlayersDao {
        return database.playersDao()
    }
}