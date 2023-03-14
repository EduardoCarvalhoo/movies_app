package com.example.movies_app.domain.useCase

import com.example.movies_app.domain.result.Result
import com.example.movies_app.domain.model.Movie

interface GetMovieList {
    suspend fun call(): Result<List<Movie>>
}