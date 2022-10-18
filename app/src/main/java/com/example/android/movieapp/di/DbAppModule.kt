package com.example.android.movieapp.di

import android.content.Context
import androidx.room.Room
import com.example.android.movieapp.database.UserCredentialsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbAppModule {

    @Singleton
    @Provides
    fun provideUserCredentialsDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, UserCredentialsDatabase::class.java,"userCredentials_history_database").build()


     @Singleton
     @Provides
     fun provideUserCredentialsDao(database: UserCredentialsDatabase) = database.getUserCredentialsDao()
}




