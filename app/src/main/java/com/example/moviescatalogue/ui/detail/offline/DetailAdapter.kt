package com.example.moviescatalogue.ui.detail.offline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviescatalogue.data.local.entity.DetailTopCastEntity
import com.example.moviescatalogue.databinding.ItemDetailOfflineBinding
import com.example.moviescatalogue.databinding.ItemMoviesBinding
import com.example.moviescatalogue.ui.movies.MoviesAdapter

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {
    private val listTopCast = ArrayList<DetailTopCastEntity>()

    fun setTopCast(topCast: List<DetailTopCastEntity>?) {
        if (topCast == null) return
        listTopCast.apply {
            clear()
            addAll(topCast)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailViewHolder {
        val view = ItemDetailOfflineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val topCast = listTopCast[position]
        holder.bind(topCast)
    }

    override fun getItemCount(): Int = listTopCast.size

    class DetailViewHolder(private val binding: ItemDetailOfflineBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(topCast: DetailTopCastEntity) {
            binding.apply {
                detailTopCast = topCast
                executePendingBindings()
            }
        }
    }
}