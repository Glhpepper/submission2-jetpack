package com.example.moviescatalogue.ui.main.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescatalogue.R
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.databinding.ItemMoviesBinding
import com.example.moviescatalogue.ui.main.MainFragmentDirections
import com.example.moviescatalogue.ui.main.di.MainScope
import kotlinx.android.synthetic.main.item_movies.view.*
import javax.inject.Inject

@MainScope
class MoviesAdapter @Inject constructor(): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private val listMovies = ArrayList<MoviesEntity>()
    private var lastPosition = -1

    fun setMovies(movies: List<MoviesEntity>?) {
        if (movies == null) return
        listMovies.apply {
            clear()
            addAll(movies)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        val view = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
        setAnimation(holder.itemView.cv_movies, position)
    }

    override fun getItemCount(): Int = listMovies.size

    private fun setAnimation(view: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(view.context, R.anim.fade_in)
            view.startAnimation(animation)
            lastPosition = position
        }
    }

    class MoviesViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                navigateToDetail(binding.moviesEntity, it)
            }
        }

        private fun navigateToDetail(
            movies: MoviesEntity?,
            view: View
        ) {
            val direction =
                MainFragmentDirections.actionMainFragmentToDetailActivity(
                    movies?.moviesId,
                    null
                )
            view.findNavController().navigate(direction)
        }

        fun bind(movies: MoviesEntity?) {
            binding.apply {
                moviesEntity = movies
                executePendingBindings()
            }
        }
    }
}
