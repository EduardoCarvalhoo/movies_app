package com.example.movies_app.presentation.home

import android.accounts.NetworkErrorException
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies_app.R
import com.example.movies_app.databinding.ActivityHomeBinding
import com.example.movies_app.data.exception.ServerErrorException
import com.example.movies_app.domain.model.Movie
import com.example.movies_app.presentation.details.DetailsActivity
import com.example.movies_app.presentation.home.adapter.HomeAdapter
import com.example.movies_app.utils.showAlertDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getMovies()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.moviesLoadSuccessfullyLiveData.observe(this) { movies ->
            setupRecyclerView(movies)
        }
        viewModel.moviesLoadErrorLiveData.observe(this) { exception ->
            when (exception) {
                is NetworkErrorException -> showAlertDialog(R.string.no_internet_connection_error) {
                    viewModel.getMovies()
                }
                is ServerErrorException -> showAlertDialog(R.string.server_error) {
                    viewModel.getMovies()
                }
                else -> showAlertDialog(R.string.generic_error) {
                    viewModel.getMovies()
                }
            }
        }
    }

    private fun setupRecyclerView(movies: List<Movie>) {
        binding.homeRecyclerView.adapter = HomeAdapter(movies) { movie ->
            val intent = DetailsActivity.getStartIntent(this@HomeActivity, movie)
            startActivity(intent)
        }
    }
}