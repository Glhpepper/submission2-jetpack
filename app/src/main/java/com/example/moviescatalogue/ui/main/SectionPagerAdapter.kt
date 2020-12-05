package com.example.moviescatalogue.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.paging.ExperimentalPagingApi
import com.example.moviescatalogue.R
import com.example.moviescatalogue.ui.movies.MoviesFragment
import com.example.moviescatalogue.ui.tvshows.TvShowsFragment

class SectionPagerAdapter(private val context: Context?, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabTitles = intArrayOf(R.string.tab_movies, R.string.tab_tv_show)

    override fun getCount(): Int = tabTitles.size

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MoviesFragment()
            1 -> fragment = TvShowsFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context?.resources?.getString(tabTitles[position])
    }
}