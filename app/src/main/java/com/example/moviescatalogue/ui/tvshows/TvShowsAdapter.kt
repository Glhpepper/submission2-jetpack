package com.example.moviescatalogue.ui.tvshows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviescatalogue.R
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.data.remote.RetrofitClient.BASE_IMG
import com.example.moviescatalogue.databinding.ItemTvShowsBinding
import com.example.moviescatalogue.ui.main.MainFragmentDirections
import com.example.moviescatalogue.utils.isOnline
import kotlinx.android.synthetic.main.item_tv_shows.view.*

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {
    private val listTvShows = ArrayList<TvShowsEntity>()

    fun setTvShows(tvShows: List<TvShowsEntity>?) {
        if (tvShows == null) return
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
    }

    override fun getItemCount(): Int = listTvShows.size

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

        fun bind(tvShows: TvShowsEntity) {
            binding.apply {
                tvShowsEntity = tvShows
                executePendingBindings()
            }
        }
    }
}
