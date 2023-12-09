package com.example.movies_app.presentation.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.movies_app.R
import com.example.movies_app.databinding.ActivityDetailsBinding
import com.example.movies_app.domain.model.Movie
import com.example.movies_app.utils.EMPTY_STRING
import com.example.movies_app.utils.IMAGE_URL
import com.example.movies_app.utils.MOVIE
import com.example.movies_app.utils.getCompatParcelableExtra

class DetailsActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }
    private val movie by lazy { intent.getCompatParcelableExtra<Movie>(MOVIE) }

    companion object {
        fun getStartIntent(context: Context, movie: Movie): Intent {
            return Intent(context, DetailsActivity::class.java).apply {
                putExtra(MOVIE, movie)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupToolbar()
        configureMovieDetails()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.detailsToolbar)
        title = EMPTY_STRING
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun configureMovieDetails() {
        with(binding) {
            Glide.with(this@DetailsActivity).load(IMAGE_URL + movie?.imageUrl)
                .error(R.drawable.img_capa_indisponivel).into(detailsPhotoImageView)
            detailsTitleTextView.text = getString(R.string.details_title_text, movie?.title)
            detailsDateTextView.text = getString(R.string.details_date_text, movie?.data)
            detailsPopularityTextView.text =
                getString(R.string.details_popularity_text, movie?.popularity)
            detailsDescriptionTextView.text = movie?.synopsis
        }
    }
}