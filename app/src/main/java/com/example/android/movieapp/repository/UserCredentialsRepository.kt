package com.example.android.movieapp.repository

import com.example.android.movieapp.database.UserCredentials
import com.example.android.movieapp.database.UserCredentialsDao
import javax.inject.Inject
import javax.inject.Singleton


class UserCredentialsRepository
@Inject
constructor(private val userCredentialsDao: UserCredentialsDao) {



    suspend fun insertUser(user: UserCredentials): Long = userCredentialsDao.insertUser(user)

    suspend fun verifyLoginData(userName: String, password: String): UserCredentials? =
        userCredentialsDao.verifyLoginData(userName, password)

    //suspend fun getUserById(key: Long): UserCredentials? = userCredentialsDao.getUserById(key)

}