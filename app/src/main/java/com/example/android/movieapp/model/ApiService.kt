package com.example.android.movieapp.model

import com.example.android.movieapp.helper.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getMovies():Response<MovieResponse>

}