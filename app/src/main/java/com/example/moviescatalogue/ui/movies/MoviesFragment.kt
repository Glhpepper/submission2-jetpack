package com.example.moviescatalogue.ui.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.moviescatalogue.R
import com.example.moviescatalogue.databinding.FragmentMoviesBinding
import com.example.moviescatalogue.ui.main.MainActivity
import com.example.moviescatalogue.ui.main.MainViewModel
import com.example.moviescatalogue.utils.isOnline
import javax.inject.Inject


class MoviesFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }
    private lateinit var binding: FragmentMoviesBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(inflater, R.layout.fragment_movies, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvMovies.adapter = MoviesAdapter()
            rvMovies.setHasFixedSize(true)
            moviesViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        setupMovies()
    }

    private fun setupMovies() {
        if (isOnline(context)) {
            viewModel.getMoviesApi()
        } else {
            viewModel.getMovies()
        }
    }

}