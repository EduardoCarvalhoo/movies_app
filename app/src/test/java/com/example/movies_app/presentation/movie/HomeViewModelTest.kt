package com.example.movies_app.presentation.movie


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movies_app.R
import com.example.movies_app.data.repository.MoviesRepository
import com.example.movies_app.data.response.MoviesResult
import com.example.movies_app.domain.model.Movie
import com.example.movies_app.presentation.ui.home.HomeViewModel
import com.example.movies_app.utils.NoInternetException
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val moviesRepository: MoviesRepository = mockk()
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(moviesRepository, Dispatchers.Unconfined)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN a call on getMovieList WHEN repository return success THEN post success data on moviesLoadSuccessfullyLiveData`() =
        runTest {
            // given
            val resultSuccess = slot<(MoviesResult) -> Unit>()
            val movies = mockk<List<Movie>>()
            coEvery {
                moviesRepository.getMovieList(capture(resultSuccess))
            } coAnswers {
                resultSuccess.captured.invoke(MoviesResult.Success(movies))
            }

            // when
            viewModel.getMovies()

            //then
            coVerify(exactly = 1) { moviesRepository.getMovieList(any()) }
            assertEquals(movies, viewModel.moviesLoadSuccessfullyLiveData.value)
        }

    @Test
    fun `GIVEN a call to getMovieList WHEN repository returns error THEN post error data to moviesLoadErrorLiveData`() =
        runTest {
            //given
            val resultError = slot<(MoviesResult) -> Unit>()

            coEvery {
                moviesRepository.getMovieList(capture(resultError))
            } coAnswers {
                resultError.captured.invoke(MoviesResult.Error(NoInternetException))
            }

            //when
            viewModel.getMovies()

            //then
            coVerify(exactly = 1) { moviesRepository.getMovieList(any()) }
            assertEquals(
                R.string.no_internet_connection_error,
                viewModel.moviesLoadErrorLiveData.value
            )
        }
}