package com.example.moviescatalogue.ui.favorite.tvshows

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.moviescatalogue.R
import com.example.moviescatalogue.databinding.FragmentFavoriteShowsBinding
import com.example.moviescatalogue.databinding.FragmentTvShowsBinding

class FavoriteShowsFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteShowsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_shows, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}