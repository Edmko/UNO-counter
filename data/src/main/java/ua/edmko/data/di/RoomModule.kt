package ua.edmko.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.edmko.data.local.AppDatabase
import ua.edmko.data.local.GameDao
import ua.edmko.data.local.PlayersDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "uno_game.db").build()
    }

    @Singleton
    @Provides
    fun providePlayersDao(database: AppDatabase): PlayersDao {
        return database.playersDao()
    }

    @Singleton
    @Provides
    fun provideGameDao(database: AppDatabase): GameDao {
        return database.gameDao()
    }
}