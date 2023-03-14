package com.example.movies_app.data.remote.movie.dataSource

import com.example.movies_app.data.remote.movie.mapper.toDomain
import com.example.movies_app.data.remote.movie.model.MovieListResponse
import com.example.movies_app.domain.result.Result
import com.example.movies_app.data.remote.rest.MovieApiService
import com.example.movies_app.data.exception.NetworkErrorException
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class MovieApiDataSourceImplTest {

    private val service: MovieApiService = mockk()
    private lateinit var movieApiDataSourceImpl: MovieApiDataSourceImpl

    @Before
    fun setup() {
        movieApiDataSourceImpl = MovieApiDataSourceImpl(service)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN call to get MyMovies THEN call the service`() =
        runTest {
            // given
            val movieListResponse =
                MovieListResponse(results = listOf(mockk(relaxed = true), mockk(relaxed = true)))

            coEvery { service.getMyMovies() } returns movieListResponse

            // when
            movieApiDataSourceImpl.getMovies()

            //then
            coVerify(exactly = 1) { service.getMyMovies() }
        }

    @Test
    fun `GIVEN a call to getMovies WHEN service returns authorized code THEN returns mapped response`() =
        runTest {
            // given
            val movieListResponse = MovieListResponse(
                results = listOf(mockk(relaxed = true), mockk(relaxed = true))
            )
            val expected = Result.Success(movieListResponse.results.toDomain())

            coEvery { service.getMyMovies() } returns movieListResponse

            // when
            val result = movieApiDataSourceImpl.getMovies()

            // then
            coVerify(exactly = 1) { service.getMyMovies() }
            assertEquals(expected, result)
        }

    @Test
    fun `GIVEN call to getMyMovies WHEN the service returns the rogue code THEN it returns the error`() =
        runTest {
            // given
            coEvery { service.getMyMovies() } throws IOException()

            // when
            val result = movieApiDataSourceImpl.getMovies()

            // then
            assertTrue(result is Result.Error && result.value is NetworkErrorException)
        }


}