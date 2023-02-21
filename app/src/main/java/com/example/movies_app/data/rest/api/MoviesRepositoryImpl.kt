package com.example.movies_app.data.rest.api

import com.example.movies_app.data.repository.MoviesRepository
import com.example.movies_app.data.response.MoviesResult
import com.example.movies_app.data.rest.service.MovieService
import com.example.movies_app.domain.model.Movie
import com.example.movies_app.utils.NoInternetException
import com.example.movies_app.utils.ServerException
import com.example.movies_app.utils.UnauthorizedException
import java.io.IOException
import java.net.HttpURLConnection

class MoviesRepositoryImpl(private val service: MovieService) : MoviesRepository {

    override suspend fun getMovieList(movieListCallback: (result: MoviesResult) -> Unit) {
        try {
            val response = service.getMyMovies().execute()
            when {
                response.isSuccessful -> {
                    val movieList = response.body()?.results?.map {
                        Movie(
                            it.title.toString(),
                            it.imageUrl.toString(),
                            it.evaluation.toString(),
                            it.synopsis.toString(),
                            it.data.toString(),
                            it.popularity.toString()
                        )
                    }
                    movieList?.let {
                        movieListCallback(MoviesResult.Success(it))
                    }
                }
                response.code() == HttpURLConnection.HTTP_UNAUTHORIZED -> {
                    movieListCallback(MoviesResult.Error(UnauthorizedException))
                }
            }
        } catch (t: Throwable) {
            movieListCallback(
                MoviesResult.Error(
                    if (t is IOException) NoInternetException else ServerException
                )
            )
        }
    }
}