package com.example.moviescatalogue.ui.main.tvshows

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.example.moviescatalogue.R
import com.example.moviescatalogue.databinding.FragmentTvShowsBinding
import com.example.moviescatalogue.ui.main.MainActivity
import com.example.moviescatalogue.ui.main.MainViewModel
import com.example.moviescatalogue.ui.main.movies.MoviesAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class TvShowsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }
    private lateinit var binding: FragmentTvShowsBinding
    @Inject
    lateinit var showsAdapter: TvShowsAdapter
    private var showsJob: Job? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, R.layout.fragment_tv_shows, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvTvShows.adapter = showsAdapter
            rvTvShows.setHasFixedSize(true)
            showsViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        setupShows()
    }

    private fun setupShows() {
        showsJob?.cancel()
        showsJob = lifecycleScope.launch {
            viewModel.getShowsApiPaging()
            viewModel.listShowsApi.observe(viewLifecycleOwner) { shows ->
                showsAdapter.submitData(lifecycle, shows)
            }
        }
    }
}
