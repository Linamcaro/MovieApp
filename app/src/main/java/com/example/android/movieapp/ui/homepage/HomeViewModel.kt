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

    private val currentPage = MutableLiveData<Int>().apply { value = 1 }
    //indicates whether the function is currently loading the next page or not.
    private var isLoading = false

    private val itemsPerPage = 20

    private val _response = MutableLiveData<List<MovieItem>>()
    val responseMovie : LiveData<List<MovieItem>>
        get() = _response

    init{
        getMoviesList()
    }


    private fun getMoviesList() {
        viewModelScope.launch {
            repository.getMovies(currentPage.value!!,itemsPerPage).let { response ->
                if(response.isSuccessful){
                    _response.postValue(response.body())
                }else{
                    Log.d("Tag", "getMovies Error: ${response.code()}")
                }
                isLoading = false
            }
        }
    }

    //if the user scrolled to the end of the page, go to the next page of data
    fun changePage(){
        //check if is loading to prevent changePage() and getMoviesList() from being called multiple times.
        if (!isLoading) {
            isLoading = true
            currentPage.value = currentPage.value?.plus(1)
            getMoviesList()
        }
    }


   /* fun previousPage() {
        if (!isLoading) {
            if (currentPage.value != 1) {
                currentPage.value = currentPage.value?.minus(1)
                _response.postValue(pages[currentPage.value!! - 1])
            }
        }
    }*/
}