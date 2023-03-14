package com.example.movies_app.presentation.ui.home


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movies_app.domain.result.Result
import com.example.movies_app.domain.model.Movie
import com.example.movies_app.domain.useCase.GetMovieList
import com.example.movies_app.presentation.home.HomeViewModel
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

    private val getMovieList: GetMovieList = mockk()
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(getMovieList, Dispatchers.Unconfined)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN a call on getMovieList WHEN repository return success THEN post success data on moviesLoadSuccessfullyLiveData`() =
        runTest {
            // given
            val movieList = mockk<List<Movie>>()

            coEvery { getMovieList.call() } returns Result.Success(movieList)

            // when
            viewModel.getMovies()

            //then
            coVerify(exactly = 1) { getMovieList.call() }
            assertEquals(movieList, viewModel.moviesLoadSuccessfullyLiveData.value)
        }

    @Test
    fun `GIVEN a call to getMovieList WHEN repository returns error THEN post error data to moviesLoadErrorLiveData`() =
        runTest {
            val expected = mockk<Exception>()
            //given
            coEvery { getMovieList.call() } returns Result.Error(expected)

            //when
            viewModel.getMovies()

            //then
            coVerify(exactly = 1) { getMovieList.call() }
            assertEquals(expected, viewModel.moviesLoadErrorLiveData.value)
        }
}