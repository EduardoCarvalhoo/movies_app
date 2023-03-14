package com.example.movies_app.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_app.domain.result.Result
import com.example.movies_app.domain.model.Movie
import com.example.movies_app.domain.useCase.GetMovieList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMovieList: GetMovieList,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _moviesLoadSuccessfullyMutableLiveData: MutableLiveData<List<Movie>> =
        MutableLiveData()
    val moviesLoadSuccessfullyLiveData: LiveData<List<Movie>> =
        _moviesLoadSuccessfullyMutableLiveData
    private val _moviesLoadErrorMutableLiveData = MutableLiveData<Throwable>()
    val moviesLoadErrorLiveData: LiveData<Throwable> = _moviesLoadErrorMutableLiveData

    fun getMovies() {
        viewModelScope.launch(dispatcher) {
            when (val result = getMovieList.call()) {
                is Result.Success -> {
                    _moviesLoadSuccessfullyMutableLiveData.postValue(result.data)
                }
                is Result.Error -> {
                    _moviesLoadErrorMutableLiveData.postValue(result.value)
                }
            }
        }
    }
}
