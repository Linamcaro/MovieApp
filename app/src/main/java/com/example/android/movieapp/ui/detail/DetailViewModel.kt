package com.example.android.movieapp.ui.detail

import android.graphics.Movie
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.example.android.movieapp.model.MovieItem
import com.example.android.movieapp.repository.MovieRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel : ViewModel() {

    fun convertToJson(movie: MovieItem) {
        val json = Gson().toJson(movie)
    }

}