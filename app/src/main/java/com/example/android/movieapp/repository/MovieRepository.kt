package com.example.android.movieapp.repository

import com.example.android.movieapp.model.ApiService
import javax.inject.Inject

class MovieRepository
@Inject
constructor(private val apiService: ApiService){

    suspend fun getMovies(page: Int, limit: Int) = apiService.getMovies(page, limit)
}