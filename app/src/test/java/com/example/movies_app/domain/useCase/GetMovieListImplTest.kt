package com.example.movies_app.domain.useCase

import com.example.movies_app.domain.result.Result
import com.example.movies_app.data.repository.MoviesRepository
import com.example.movies_app.domain.model.Movie
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieListImplTest {

    private val moviesRepository: MoviesRepository = mockk()
    private lateinit var getMovieListImpl: GetMovieListImpl

    @Before
    fun setup() {
        getMovieListImpl = GetMovieListImpl(moviesRepository)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }


    @Test
    fun `WHEN getMovieListImpl is called THEN it should return repository result`() =
        runTest {
            // given
            val movie = listOf<Movie>(mockk(relaxed = true))
            val expected = Result.Success(movie)

            coEvery { getMovieListImpl.call() } returns expected

            // when
            val result = getMovieListImpl.call()

            //then
            coVerify(exactly = 1) { getMovieListImpl.call() }
            assertEquals(expected, result)
        }
}