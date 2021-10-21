package ua.edmko.unocounter.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.edmko.navigation.NavigationManager
import ua.edmko.navigation.NavigationManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideGson() = Gson()

}