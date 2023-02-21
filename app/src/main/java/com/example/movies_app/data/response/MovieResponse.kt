package com.example.movies_app.data.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("original_title")
    val title: String?,
    @SerializedName("poster_path")
    val imageUrl: String?,
    @SerializedName("vote_average")
    val evaluation: String?,
    @SerializedName("overview")
    val synopsis: String?,
    @SerializedName("release_date")
    val data: String?,
    @SerializedName("popularity")
    val popularity: String?
)