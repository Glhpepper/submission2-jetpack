package com.example.moviescatalogue.ui.favorite

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviescatalogue.R
import com.example.moviescatalogue.ui.favorite.movies.FavoriteMoviesFragment
import com.example.moviescatalogue.ui.favorite.tvshows.FavoriteShowsFragment

class FavoritePagerAdapter(private val context: Context?, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabTitles = intArrayOf(R.string.tab_movies, R.string.tab_tv_show)

    override fun getCount(): Int = tabTitles.size

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FavoriteMoviesFragment()
            1 -> fragment = FavoriteShowsFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context?.resources?.getString(tabTitles[position])
    }
}