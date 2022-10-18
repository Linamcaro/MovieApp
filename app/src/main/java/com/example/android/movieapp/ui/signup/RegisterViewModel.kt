package com.example.android.movieapp.ui.signup

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
class RegisterViewModel
@Inject
constructor(private val repository: UserCredentialsRepository) : ViewModel() {

    //Live data for new user
    private var _newUser = MutableLiveData<UserCredentials?>()
    val newUser: LiveData<UserCredentials?>
        get() = _newUser

    //Check when signIn button is clicked
    private val _onSignUp = MutableLiveData<Boolean>()
    val onSignUp: LiveData<Boolean>
        get() = _onSignUp

    //Check when signIn button is clicked
    private val _onSignIn = MutableLiveData<Boolean>()
    val onSignIn: LiveData<Boolean>
        get() = _onSignIn


    private suspend fun registerNewUser(user: UserCredentials) {
        withContext(Dispatchers.IO) {
            repository.insertUser(user)
        }
    }

    //triggered with the signup button
    fun validation(user: UserCredentials) {

        when {
            user.name.isEmpty() -> {
                _newUser.value = null
            }
            user.password.isEmpty() -> {
                _newUser.value = null
            }
            else -> {
                viewModelScope.launch {

                    registerNewUser(user)
                    _newUser.value = user
                }
            }
        }
    }

    //Buttons
    fun onSignUpClick() {
        _onSignUp.value = true
    }

    fun onLoginClick() {
        _onSignIn.value = true
    }
}