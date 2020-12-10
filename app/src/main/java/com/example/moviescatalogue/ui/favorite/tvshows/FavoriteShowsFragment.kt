package com.example.moviescatalogue.ui.favorite.tvshows

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescatalogue.R
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.databinding.FragmentFavoriteShowsBinding
import com.example.moviescatalogue.databinding.FragmentTvShowsBinding
import com.example.moviescatalogue.ui.favorite.FavoriteFragment
import com.example.moviescatalogue.ui.favorite.FavoriteViewModel
import com.example.moviescatalogue.ui.favorite.movies.FavoriteMoviesAdapter
import com.example.moviescatalogue.utils.SwipeToDelete
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favorite_movies.*
import kotlinx.android.synthetic.main.fragment_favorite_shows.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteShowsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<FavoriteViewModel> { viewModelFactory }
    private lateinit var binding: FragmentFavoriteShowsBinding

    @Inject
    lateinit var favoriteShowsAdapter: FavoriteShowsAdapter
    private var movieJob: Job? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (parentFragment as FavoriteFragment).favoriteComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_shows, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            rvFavoriteShows.adapter = favoriteShowsAdapter
            rvFavoriteShows.setHasFixedSize(true)
            showsViewModel = viewModel
        }

        favoriteShowsList()
        swipeDeleteShows()
    }

    private fun swipeDeleteShows() {
        val swipeToDelete = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val favoritePosition = favoriteShowsAdapter.getFavoriteAtPosition(position)
                viewModel.deleteFavoriteShows(favoritePosition)
                favoriteShowsList()
                parentFragment?.requireView()
                    ?.let { Snackbar.make(it, R.string.state_false, Snackbar.LENGTH_SHORT).show() }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(rv_favorite_shows)
    }

    private fun favoriteShowsList() {
        movieJob?.cancel()
        movieJob = lifecycleScope.launch {
            viewModel.getFavoriteShowsPaging()
            viewModel.favoriteShows.observe(viewLifecycleOwner, { favorite ->
                favoriteShowsAdapter.submitData(lifecycle, favorite)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        favoriteShowsList()
    }
}