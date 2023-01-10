package com.example.android.movieapp.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: Image,
    @SerializedName("name")
    val name: String,
    @SerializedName("summary")
    val summary: String
):Serializable