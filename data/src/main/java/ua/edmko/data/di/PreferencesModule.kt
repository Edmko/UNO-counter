package ua.edmko.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.edmko.data.preferences.DataStoreManager
import ua.edmko.domain.repository.Preferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class PreferencesModule {

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context): Preferences {
        return DataStoreManager(context)
    }
}
