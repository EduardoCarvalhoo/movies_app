package com.example.movies_app.data.remote.rest

import com.example.movies_app.data.remote.movie.model.MovieListResponse
import com.example.movies_app.utils.POPULAR_MOVIES
import retrofit2.http.GET

interface MovieApiService {
    @GET(POPULAR_MOVIES)
    suspend fun getMyMovies(): MovieListResponse
}