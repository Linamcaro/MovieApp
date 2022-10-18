package com.example.android.movieapp.ui.homepage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.movieapp.repository.MovieRepository
import com.example.android.movieapp.model.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(private val repository: MovieRepository) : ViewModel() {

    private val _response = MutableLiveData<List<MovieItem>>()
    val responseMovie : LiveData<List<MovieItem>>
        get() = _response

    init{
        getMoviesList()
    }


    private fun getMoviesList() {
        viewModelScope.launch {
            repository.getMovies().let { response ->
                if(response.isSuccessful){
                    _response.postValue(response.body())
                }else{
                    Log.d("Tag", "getMovies Error: ${response.code()}")
                }
            }
        }
    }
}