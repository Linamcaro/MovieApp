package com.example.android.movieapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [UserCredentials::class], version = 1, exportSchema = false)
abstract class UserCredentialsDatabase : RoomDatabase() {


    abstract fun getUserCredentialsDao(): UserCredentialsDao

}