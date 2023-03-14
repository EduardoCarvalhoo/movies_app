package com.example.movies_app.data.repository

import com.example.movies_app.data.remote.movie.dataSource.MovieApiDataSource
import com.example.movies_app.domain.result.Result
import com.example.movies_app.domain.model.Movie
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesRepositoryImplTest {

    private val movieApiDataSource: MovieApiDataSource = mockk()
    private lateinit var movieRepositoryImpl: MoviesRepositoryImpl

    @Before
    fun setupTest() {
        movieRepositoryImpl = MoviesRepositoryImpl(movieApiDataSource)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN a call to getMovieList THEN return apiDataSource result`() = runTest {

        val expected = mockk<Result<List<Movie>>>()

        coEvery { movieRepositoryImpl.getMovieList() } returns expected

        val result = movieRepositoryImpl.getMovieList()

        assertEquals(expected, result)
    }
}