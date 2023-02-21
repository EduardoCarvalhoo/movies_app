package com.example.movies_app.data.repository

import com.example.movies_app.data.response.MoviesResult

interface MoviesRepository {
    suspend fun getMovieList(movieListCallback: (result: MoviesResult) -> Unit)
}