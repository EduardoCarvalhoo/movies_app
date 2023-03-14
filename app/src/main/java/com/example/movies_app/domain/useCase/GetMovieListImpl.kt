package com.example.movies_app.domain.useCase

import com.example.movies_app.data.repository.MoviesRepository
import com.example.movies_app.domain.result.Result
import com.example.movies_app.domain.model.Movie

class GetMovieListImpl(private val moviesRepository: MoviesRepository): GetMovieList {
    override suspend fun call(): Result<List<Movie>> {
        return moviesRepository.getMovieList()
    }
}