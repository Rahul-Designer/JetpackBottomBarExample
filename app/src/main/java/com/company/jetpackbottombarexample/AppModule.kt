package com.company.jetpackbottombarexample

import com.company.jetpackbottombarexample.auth.navigation.NavigationManager
import com.company.jetpackbottombarexample.auth.navigation.NavigationManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNavigationManager(): NavigationManager {
        return NavigationManagerImpl()
    }
}