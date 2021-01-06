package com.example.moviescatalogue.ui.main.tvshows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescatalogue.R
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.databinding.ItemTvShowsBinding
import com.example.moviescatalogue.ui.main.MainFragmentDirections
import com.example.moviescatalogue.ui.main.di.MainScope
import kotlinx.android.synthetic.main.item_tv_shows.view.*
import javax.inject.Inject

@MainScope
class TvShowsAdapter @Inject constructor() : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {
    private val listTvShows = ArrayList<TvShowsEntity>()
    private var lastPosition = -1

    fun setTvShows(tvShows: List<TvShowsEntity>?) {
        if (tvShows.isNullOrEmpty()) return
        listTvShows.apply {
            clear()
            addAll(tvShows)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowsViewHolder {
        val view = ItemTvShowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val tvShows = listTvShows[position]
        holder.bind(tvShows)
        setAnimation(holder.itemView.cv_tv_shows, position)
    }

    override fun getItemCount(): Int = listTvShows.size

    private fun setAnimation(view: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(view.context, R.anim.fade_in)
            view.startAnimation(animation)
            lastPosition = position
        }
    }

    class TvShowsViewHolder(private val binding: ItemTvShowsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                navigateToDetail(binding.tvShowsEntity, it)
            }
        }

        private fun navigateToDetail(
            tvShows: TvShowsEntity?,
            view: View
        ) {
            val direction =
                MainFragmentDirections.actionMainFragmentToDetailActivity(
                    null,
                    tvShows?.showsId
                )
            view.findNavController().navigate(direction)
        }

        fun bind(tvShows: TvShowsEntity?) {
            binding.apply {
                tvShowsEntity = tvShows
                executePendingBindings()
            }
        }
    }
}
