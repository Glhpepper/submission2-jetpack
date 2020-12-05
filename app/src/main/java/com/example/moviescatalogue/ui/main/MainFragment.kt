package com.example.moviescatalogue.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.example.moviescatalogue.R
import com.example.moviescatalogue.databinding.FragmentMainBinding
import com.example.moviescatalogue.ui.main.di.MainScope
import com.example.moviescatalogue.ui.movies.MoviesAdapter
import com.example.moviescatalogue.ui.tvshows.TvShowsAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

@MainScope
class MainFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel by viewModels<MainViewModel> { viewModelFactory }
    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var moviesAdapter: MoviesAdapter
    @Inject
    lateinit var showsAdapter: TvShowsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setUpPager()
        btn_retry.setOnClickListener {
            moviesAdapter.retry()
            showsAdapter.retry()
        }
    }

    private fun setUpPager() {
        val sectionPagerAdapter = SectionPagerAdapter(context, childFragmentManager)
        tab_layout.setupWithViewPager(view_pager)
        view_pager.adapter = sectionPagerAdapter

        moviesAdapter.addLoadStateListener { loadState ->
            pb_main.isVisible = loadState.source.refresh is LoadState.Loading
            btn_retry.isVisible = loadState.source.refresh is LoadState.Error
        }
        showsAdapter.addLoadStateListener { loadState ->
            pb_main.isVisible = loadState.source.refresh is LoadState.Loading
            btn_retry.isVisible = loadState.source.refresh is LoadState.Error
        }
    }
}