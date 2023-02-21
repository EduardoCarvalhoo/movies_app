package com.example.movies_app.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies_app.R
import com.example.movies_app.databinding.ItemMovieBinding
import com.example.movies_app.domain.model.Movie
import com.example.movies_app.utils.IMAGE_URL

class HomeAdapter(
    private var movieList: List<Movie>,
    private val onItemClickListener: (item: Movie) -> Unit
) : RecyclerView.Adapter<HomeAdapter.MovieListingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListingViewHolder {
        val view = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return MovieListingViewHolder(view, onItemClickListener)
    }
    override fun onBindViewHolder(holder: MovieListingViewHolder, position: Int) {
        holder.bindView(movieList[position])
    }

    override fun getItemCount() = movieList.size

    class MovieListingViewHolder(
        binding: ItemMovieBinding,
        private val onItemClickListener: (item: Movie) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private val image = binding.itemMovieCoverImage
        private val title = binding.itemMovieTitleTextView
        private val evaluation = binding.itemMovieEvaluationTextView

        fun bindView(item: Movie) {
            Glide.with(this@MovieListingViewHolder.itemView).load(IMAGE_URL + item.imageUrl)
                .error(R.drawable.img_capa_indisponivel).into(image)
            title.text = item.title
            evaluation.text = itemView.context.getString(R.string.home_evaluation, item.evaluation)

            itemView.setOnClickListener {
                onItemClickListener.invoke(item)
            }
        }
    }
}
