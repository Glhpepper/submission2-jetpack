package com.example.moviescatalogue.ui.favorite.tvshows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescatalogue.R
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.databinding.ItemFavoriteShowsBinding
import com.example.moviescatalogue.ui.favorite.FavoriteFragmentDirections
import com.example.moviescatalogue.ui.favorite.di.FavoriteScope
import kotlinx.android.synthetic.main.item_favorite_movies.view.*
import kotlinx.android.synthetic.main.item_favorite_shows.view.*
import javax.inject.Inject

@FavoriteScope
class FavoriteShowsAdapter @Inject constructor() :
    PagingDataAdapter<FavoriteShows, FavoriteShowsAdapter.FavoriteViewHolder>(Shows_DiffUtils) {
    private var lastPosition = -1

    companion object {
        private val Shows_DiffUtils = object : DiffUtil.ItemCallback<FavoriteShows>() {
            override fun areItemsTheSame(oldItem: FavoriteShows, newItem: FavoriteShows): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: FavoriteShows, newItem: FavoriteShows): Boolean =
                oldItem == newItem
        }
    }

    fun getFavoriteAtPosition(position: Int): FavoriteShows? {
        return getItem(position)
    }

    class FavoriteViewHolder(private val binding: ItemFavoriteShowsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                navigateToDetail(binding.showsEntity, it)
            }
        }

        private fun navigateToDetail(
            favoriteShows: FavoriteShows?,
            view: View
        ) {
            val direction =
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailActivity(
                    null,
                    favoriteShows?.id.toString()
                )
            view.findNavController().navigate(direction)
        }

        fun bind(favoriteShows: FavoriteShows?) {
            binding.showsEntity = favoriteShows
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ItemFavoriteShowsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val userFavoriteProperties = getItem(position)
        holder.bind(userFavoriteProperties)
        setAnimation(holder.itemView.cv_favorite_shows, position)
    }

    private fun setAnimation(view: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(view.context, R.anim.fade_in)
            view.startAnimation(animation)
            lastPosition = position
        }
    }
}