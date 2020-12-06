package com.example.moviescatalogue.ui.favorite

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import com.example.moviescatalogue.R
import com.example.moviescatalogue.databinding.FragmentFavoriteBinding
import com.example.moviescatalogue.ui.main.SectionPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_main.*

class FavoriteFragment : Fragment() {

    private lateinit var binding:FragmentFavoriteBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setUpPager()
    }

    private fun setUpPager() {
        val favoritePagerAdapter = FavoritePagerAdapter(context, childFragmentManager)
        tab_layout_favorite.setupWithViewPager(view_pager_favorite)
        view_pager_favorite.adapter = favoritePagerAdapter

    }
}