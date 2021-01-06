package com.example.moviescatalogue.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.moviescatalogue.MyApplication
import com.example.moviescatalogue.R
import com.example.moviescatalogue.databinding.FragmentFavoriteBinding
import com.example.moviescatalogue.ui.favorite.di.FavoriteComponent
import kotlinx.android.synthetic.main.fragment_favorite.*
import javax.inject.Inject

class FavoriteFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val favoriteViewModel by viewModels<FavoriteViewModel> { viewModelFactory }
    private lateinit var binding: FragmentFavoriteBinding
    lateinit var favoriteComponent: FavoriteComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        favoriteComponent =
            (requireActivity().application as MyApplication).appComponent.favoriteComponent()
                .create()
        favoriteComponent.inject(this)
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