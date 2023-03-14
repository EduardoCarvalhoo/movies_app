package com.example.movies_app.data.repository

import com.example.movies_app.domain.result.Result
import com.example.movies_app.domain.model.Movie

interface MoviesRepository {
    suspend fun getMovieList(): Result<List<Movie>>
}