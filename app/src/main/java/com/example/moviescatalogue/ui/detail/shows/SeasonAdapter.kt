package com.example.moviescatalogue.ui.detail.shows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescatalogue.data.remote.response.SeasonsItem
import com.example.moviescatalogue.databinding.ItemDetailShowsBinding

class SeasonAdapter : RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder>() {
    private val listSeason = ArrayList<SeasonsItem>()

    fun setListSeason(seasonsItem: List<SeasonsItem>?) {
        if (seasonsItem == null) return
        listSeason.apply {
            clear()
            addAll(seasonsItem)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeasonViewHolder {
        val view = ItemDetailShowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeasonViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val season = listSeason[position]
        holder.bind(season)
    }

    override fun getItemCount(): Int = listSeason.size

    class SeasonViewHolder(private val binding: ItemDetailShowsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(seasons: SeasonsItem) {
            binding.apply {
                listSeason = seasons
                executePendingBindings()
            }
        }
    }
}