package com.example.movies_app.data.remote.movie.dataSource

import com.example.movies_app.domain.result.Result
import com.example.movies_app.domain.model.Movie

interface MovieApiDataSource {
   suspend fun getMovies(): Result<List<Movie>>
}