package com.example.android.movieapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.movieapp.database.UserCredentials
import com.example.android.movieapp.repository.UserCredentialsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(private val repository: UserCredentialsRepository) : ViewModel() {

    //Live data for the Login credentials
    private var _loginData = MutableLiveData<UserCredentials?>()
    val loginData: LiveData<UserCredentials?>
        get() = _loginData

    //Check if user is logged in
    private val _loggedIn = MutableLiveData<Boolean>()
    val loggedIn: LiveData<Boolean>
        get() = _loggedIn

    //Check when signIn button is clicked
    private val _onSignIn = MutableLiveData<Boolean>()
    val onSignIn: LiveData<Boolean>
        get() = _onSignIn

    //Check when signIn button is clicked
    private val _onSignUp = MutableLiveData<Boolean>()
    val onSignUp: LiveData<Boolean>
        get() = _onSignUp


    init {
        _onSignIn.value = false
        _onSignUp.value = false
        _loggedIn.value = false
    }

    //get info from database
    private suspend fun getUserLoginData(userName: String, password: String) {
        withContext(Dispatchers.IO) {
            val data = repository.verifyLoginData(userName, password)
            _loginData.postValue(data)
        }
    }

    //check credentials
    fun validation(name: String, password: String) {
        viewModelScope.launch {
            getUserLoginData(name, password)
        }
    }

    //Buttons
    fun onLoginClick() {
        _onSignIn.value = true
    }

    fun onSignUpClick() {
        _onSignUp.value = true
    }

    fun isLoggedIn() {
        _loggedIn.postValue(true)
    }


}
