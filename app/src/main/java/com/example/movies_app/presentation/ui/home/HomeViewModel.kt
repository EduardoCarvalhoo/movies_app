package com.example.movies_app.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies_app.R
import com.example.movies_app.data.repository.MoviesRepository
import com.example.movies_app.data.response.MoviesResult
import com.example.movies_app.domain.model.Movie
import com.example.movies_app.utils.NoInternetException
import com.example.movies_app.utils.UnauthorizedException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    private val moviesLoadSuccessfullyMutableLiveData = MutableLiveData<List<Movie>>()
    val moviesLoadSuccessfullyLiveData: LiveData<List<Movie>> =
        moviesLoadSuccessfullyMutableLiveData
    private val moviesLoadErrorMutableLiveData = MutableLiveData<Int>()
    val moviesLoadErrorLiveData: LiveData<Int> = moviesLoadErrorMutableLiveData


    fun getMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            moviesRepository.getMovieList { result: MoviesResult ->
                when (result) {
                    is MoviesResult.Success -> {
                        moviesLoadSuccessfullyMutableLiveData.postValue(result.value)
                    }
                    is MoviesResult.Error -> {
                        when (result.value) {
                            is NoInternetException -> {
                                moviesLoadErrorMutableLiveData.postValue(R.string.no_internet_connection_error)
                            }
                            is UnauthorizedException -> {
                                moviesLoadErrorMutableLiveData.postValue(R.string.unauthorized_user_error)
                            }
                            else -> moviesLoadErrorMutableLiveData.postValue(R.string.server_error)
                        }
                    }
                }
            }
        }
    }
}