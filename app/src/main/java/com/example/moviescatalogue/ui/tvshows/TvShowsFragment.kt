package com.example.moviescatalogue.ui.tvshows

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
import com.example.moviescatalogue.databinding.FragmentTvShowsBinding
import com.example.moviescatalogue.ui.main.MainActivity
import com.example.moviescatalogue.ui.main.MainViewModel
import javax.inject.Inject


class TvShowsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }
    private lateinit var binding: FragmentTvShowsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(inflater, R.layout.fragment_tv_shows, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvTvShows.adapter = TvShowsAdapter()
            rvTvShows.setHasFixedSize(true)
            showsViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        setupShows()
    }

    private fun setupShows() {
        viewModel.getTvShowsApi()
    }
}
