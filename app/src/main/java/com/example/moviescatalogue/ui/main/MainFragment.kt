package com.example.moviescatalogue.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.moviescatalogue.R
import com.example.moviescatalogue.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


class MainFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel by viewModels<MainViewModel> { viewModelFactory }
    private lateinit var binding: FragmentMainBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPager()
    }

    private fun setUpPager() {
        val sectionPagerAdapter = SectionPagerAdapter(context, childFragmentManager)
        tab_layout.setupWithViewPager(view_pager)
        view_pager.adapter = sectionPagerAdapter
        showProgress(true)

        mainViewModel.listMoviesApi.observe(viewLifecycleOwner, { movies ->
            if (movies != null) showProgress(false)
        })
        mainViewModel.listShowsApi.observe(viewLifecycleOwner, { shows ->
            if (shows != null) showProgress(false)
        })
    }

    private fun showProgress(state: Boolean) {
        if (state) {
            pb_main.visibility = View.VISIBLE
        } else {
            pb_main.visibility = View.GONE
        }
    }
}