package com.example.moviescatalogue.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescatalogue.data.MainRepository_Factory
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.databinding.ItemMoviesBinding
import com.example.moviescatalogue.ui.main.MainFragment
import com.example.moviescatalogue.ui.main.MainFragmentDirections

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private val listMovies = ArrayList<MoviesEntity>()

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

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
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

        fun bind(movies: MoviesEntity) {
            binding.apply {
                moviesEntity = movies
                executePendingBindings()
            }
        }
    }
}
