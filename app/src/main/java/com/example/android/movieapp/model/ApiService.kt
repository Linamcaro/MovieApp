package com.example.android.movieapp.model

import com.example.android.movieapp.helper.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getMovies(@Query("page") page: Int,
                          @Query("limit") limit: Int):Response<MovieResponse>

}