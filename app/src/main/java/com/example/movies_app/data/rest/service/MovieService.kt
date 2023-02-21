package com.example.movies_app.data.rest.service

import com.example.movies_app.data.response.MovieListResponse
import com.example.movies_app.utils.POPULAR_MOVIES
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {
    @GET(POPULAR_MOVIES)
    fun getMyMovies(): Call<MovieListResponse>
}