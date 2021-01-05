package com.example.moviescatalogue.ui.favorite.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescatalogue.R
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.databinding.ItemFavoriteMoviesBinding
import com.example.moviescatalogue.ui.favorite.FavoriteFragmentDirections
import com.example.moviescatalogue.ui.favorite.di.FavoriteScope
import kotlinx.android.synthetic.main.item_favorite_movies.view.*
import javax.inject.Inject

@FavoriteScope
class FavoriteMoviesAdapter @Inject constructor() :
    PagedListAdapter<FavoriteMovies, FavoriteMoviesAdapter.FavoriteViewHolder>(Movie_DiffUtils) {
    private var lastPosition = -1

    companion object {
        private val Movie_DiffUtils = object : DiffUtil.ItemCallback<FavoriteMovies>() {
            override fun areItemsTheSame(oldItem: FavoriteMovies, newItem: FavoriteMovies): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: FavoriteMovies, newItem: FavoriteMovies): Boolean =
                oldItem == newItem
        }
    }

    fun getFavoriteAtPosition(position: Int): FavoriteMovies? {
        return getItem(position)
    }

    class FavoriteViewHolder(private val binding: ItemFavoriteMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                navigateToDetail(binding.moviesEntity, it)
            }
        }

        private fun navigateToDetail(
            favoriteMovies: FavoriteMovies?,
            view: View
        ) {
            val direction =
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailActivity(
                    favoriteMovies?.id.toString(),
                    null
                )
            view.findNavController().navigate(direction)
        }

        fun bind(favoriteMovies: FavoriteMovies?) {
            binding.moviesEntity = favoriteMovies
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ItemFavoriteMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val userFavoriteProperties = getItem(position)
        holder.bind(userFavoriteProperties)
        setAnimation(holder.itemView.cv_favorite_movies, position)
    }

    private fun setAnimation(view: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(view.context, R.anim.fade_in)
            view.startAnimation(animation)
            lastPosition = position
        }
    }
}