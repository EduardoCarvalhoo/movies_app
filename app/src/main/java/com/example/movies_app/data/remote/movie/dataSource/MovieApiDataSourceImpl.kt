package com.example.movies_app.data.remote.movie.dataSource

import com.example.movies_app.data.remote.movie.mapper.toDomain
import com.example.movies_app.data.remote.rest.MovieApiService
import com.example.movies_app.data.remote.rest.retrofitWrapper
import com.example.movies_app.domain.model.Movie
import com.example.movies_app.domain.result.Result

class MovieApiDataSourceImpl(private val service: MovieApiService) : MovieApiDataSource {
    override suspend fun getMovies(): Result<List<Movie>> {
        val result = retrofitWrapper {
            service.getMyMovies()
        }

        return when (result) {
            is Result.Success -> Result.Success(result.data.results.toDomain())
            is Result.Error -> result
        }
    }
}