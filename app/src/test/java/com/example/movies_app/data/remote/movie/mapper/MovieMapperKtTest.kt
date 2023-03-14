package com.example.movies_app.data.remote.movie.mapper

import com.example.movies_app.data.remote.movie.model.MovieResponse
import com.example.movies_app.domain.model.Movie
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieMapperTest {

    @Test
    fun `WHEN map a null movie list THEN return an empty list`() {
        val movieResponse: List<MovieResponse>? = null
        val result = movieResponse.toDomain()

        val expected = emptyList<Movie>()
        assertEquals(expected, result)
    }

    @Test
    fun `WHEN map a list of movies and have null attributes THEN return the mapped list with empty attributes`() {
        val movieResponse = listOf(
            MovieResponse(null, null, null, null, null, null),
        )
        val result = movieResponse.toDomain()

        val expected = listOf(
            Movie("", "", "", "", "", "")
        )
        assertEquals(expected, result)
    }

    @Test
    fun `WHEN to map a movie list THEN return the mapped list`() {
        val title = "Spider"
        val imageUrl = "http://teste.com/test"
        val evaluation = "5.0"
        val synopsis = "test"
        val data = "12/03/2023"
        val popularity = "10"

        val movieResponse = listOf(
            MovieResponse(title, imageUrl, evaluation, synopsis, data, popularity)
        )
        val result = movieResponse.toDomain()

        val expected = listOf(
            Movie(title, imageUrl, evaluation, synopsis, data, popularity)
        )
        assertEquals(expected, result)
    }
}