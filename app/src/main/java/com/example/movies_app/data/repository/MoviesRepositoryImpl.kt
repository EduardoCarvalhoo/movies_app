package com.example.movies_app.data.repository

import com.example.movies_app.data.remote.movie.dataSource.MovieApiDataSource
import com.example.movies_app.domain.result.Result
import com.example.movies_app.domain.model.Movie

class MoviesRepositoryImpl(private val movieApiDataSource: MovieApiDataSource) : MoviesRepository {
    
    override suspend fun getMovieList(): Result<List<Movie>> {
        return movieApiDataSource.getMovies()
    }
}