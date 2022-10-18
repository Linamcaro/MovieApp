package com.example.android.movieapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserCredentialsDao {
    //insert single user

    //adding new user to the database
    @Insert
    suspend fun insertUser(user: UserCredentials): Long

    //checking user exist or not in our database
    @Query("SELECT * FROM user_credentials WHERE user_name = :userName AND user_password = :password")
    suspend fun verifyLoginData(userName: String, password: String): UserCredentials?

  /*  //Selects and returns the user with given id.
    @Query("SELECT * from user_credentials WHERE userId = :key")
    suspend fun getUserById(key: Long): UserCredentials?
    */
}
