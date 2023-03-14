package com.example.movies_app.data.remote.movie.mapper

import com.example.movies_app.data.remote.movie.model.MovieResponse
import com.example.movies_app.domain.model.Movie

fun List<MovieResponse>?.toDomain(): List<Movie> {
    return this?.map { movieResponse ->
        Movie(
            movieResponse.title.orEmpty(),
            movieResponse.imageUrl.orEmpty(),
            movieResponse.evaluation.orEmpty(),
            movieResponse.synopsis.orEmpty(),
            movieResponse.data.orEmpty(),
            movieResponse.popularity.orEmpty()
        )
    } ?: listOf()
}