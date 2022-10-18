package com.example.android.movieapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.movieapp.model.Image
import java.net.PasswordAuthentication

@Entity(tableName = "user_credentials")
data class UserCredentials(
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0,

    @ColumnInfo(name = "user_name")
    var name: String,

    @ColumnInfo(name = "user_password")
    var password: String
)
