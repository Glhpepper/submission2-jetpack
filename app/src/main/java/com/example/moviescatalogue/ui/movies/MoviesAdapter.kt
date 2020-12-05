package com.example.moviescatalogue.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescatalogue.R
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.databinding.ItemMoviesBinding
import com.example.moviescatalogue.ui.main.MainFragmentDirections
import com.example.moviescatalogue.ui.main.di.MainScope
import kotlinx.android.synthetic.main.item_movies.view.*
import javax.inject.Inject

@MainScope
class MoviesAdapter @Inject constructor(): PagingDataAdapter<MoviesEntity, MoviesAdapter.MoviesViewHolder>(Movie_DiffUtils) {
    private var lastPosition = -1

    companion object {
        private val Movie_DiffUtils = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean =
                oldItem.moviesId == newItem.moviesId

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        val view = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = getItem(position)
        holder.bind(movies)
        setAnimation(holder.itemView.cv_movies, position)
    }

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
