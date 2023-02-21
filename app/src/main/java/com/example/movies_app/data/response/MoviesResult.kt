package com.example.movies_app.data.response

import com.example.movies_app.domain.model.Movie

sealed class MoviesResult {
    data class Success(val value: List<Movie>): MoviesResult()
    data class Error(val value: Throwable): MoviesResult()
}