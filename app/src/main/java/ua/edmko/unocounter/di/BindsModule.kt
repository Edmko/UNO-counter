package ua.edmko.unocounter.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.edmko.navigation.NavigationManager
import ua.edmko.navigation.NavigationManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindsModule {

    @Singleton
    @Binds
    fun bindNavigationManager(impl: NavigationManagerImpl): NavigationManager

}