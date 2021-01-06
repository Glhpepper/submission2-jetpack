package com.example.moviescatalogue.ui.favorite.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagedList
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescatalogue.R
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.databinding.FragmentFavoriteMoviesBinding
import com.example.moviescatalogue.ui.favorite.FavoriteFragment
import com.example.moviescatalogue.ui.favorite.FavoriteViewModel
import com.example.moviescatalogue.utils.SwipeToDelete
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favorite_movies.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteMoviesFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<FavoriteViewModel> { viewModelFactory }
    private lateinit var binding: FragmentFavoriteMoviesBinding

    @Inject
    lateinit var favoriteMoviesAdapter: FavoriteMoviesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (parentFragment as FavoriteFragment).favoriteComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_movies, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            rvFavoriteMovies.adapter = favoriteMoviesAdapter
            rvFavoriteMovies.setHasFixedSize(true)
        }

        favoriteMovieList()
        swipeDeleteMovies()
    }

    private fun swipeDeleteMovies() {
        val swipeToDelete = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val favoritePosition = favoriteMoviesAdapter.getFavoriteAtPosition(position)
                viewModel.deleteFavoriteMovies(favoritePosition)
                favoriteMovieList()
                parentFragment?.requireView()
                    ?.let { Snackbar.make(it, R.string.state_false, Snackbar.LENGTH_SHORT).show() }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(rv_favorite_movies)
    }

    private fun favoriteMovieList() {
        lifecycleScope.launch {
            viewModel.getFavoriteMoviePaging().observe(viewLifecycleOwner, { favoriteShows ->
                favoriteMoviesAdapter.submitList(favoriteShows)
                favoriteMoviesAdapter.notifyDataSetChanged()
                if (favoriteShows.isNullOrEmpty()){
                    showNoData(true)
                }
                else {
                    showNoData(false)
                }
            })
        }
    }

    private fun showNoData(state: Boolean) {
        if (state) {
            imageNoFavorite_movies.visibility = View.VISIBLE
        } else {
            imageNoFavorite_movies.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        favoriteMovieList()
    }
}