package com.example.android.movieapp.utils

import android.content.SharedPreferences
import com.example.android.movieapp.helper.Constants.SHARED_PREFS_FAVORITES
import com.example.android.movieapp.helper.Constants.SHARED_PREFS_IS_LOGGED_IN
import com.example.android.movieapp.helper.Constants.SHARED_PREFS_PASSWORD
import com.example.android.movieapp.helper.Constants.SHARED_PREFS_USERNAME
import javax.inject.Inject

class SharedPreferencesUtils @Inject constructor(private val sharedPreferences : SharedPreferences) {

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    //save function
    fun saveUserName(userName:String) {
        editor.putString(SHARED_PREFS_USERNAME,userName).apply()
    }

    fun savePassword(password:String) {
        editor.putString(SHARED_PREFS_PASSWORD,password).apply()
    }

    fun saveIsLoggedIn(isLoggedIn : Boolean){

        editor.putBoolean(SHARED_PREFS_IS_LOGGED_IN,isLoggedIn).apply()
    }

    fun saveFavoriteMovie(favoriteMovie : String){

        editor.putString(SHARED_PREFS_FAVORITES,favoriteMovie).apply()
    }

    //getters function
    fun getUserName(): String? {
        return sharedPreferences.getString(SHARED_PREFS_USERNAME,"")
    }

    fun getPassword(): String? {
        return sharedPreferences.getString(SHARED_PREFS_PASSWORD,"")
    }

    fun getIsLoggedIn(): Boolean{
        return sharedPreferences.getBoolean(SHARED_PREFS_IS_LOGGED_IN, false)
    }

    fun getFavoriteMovies(): String? {
        return sharedPreferences.getString(SHARED_PREFS_FAVORITES,"")
    }

}