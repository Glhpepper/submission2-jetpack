package com.example.moviescatalogue.ui.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import com.example.moviescatalogue.MyApplication
import com.example.moviescatalogue.R
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows
import com.example.moviescatalogue.databinding.ActivityDetailBinding
import com.example.moviescatalogue.ui.detail.di.DetailComponent
import com.example.moviescatalogue.ui.detail.shows.SeasonAdapter
import com.example.moviescatalogue.utils.AnimatorHelper.startAnimator
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail_movies.*
import kotlinx.android.synthetic.main.content_detail_shows.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    companion object {
        private const val INTENT_TYPE = "text/plain"
        private const val INTENT_TITLE = "Bagikan ini sekarang."
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var detailComponent: DetailComponent
    private val detailViewModel by viewModels<DetailViewModel> { viewModelFactory }
    private val args: DetailActivityArgs by navArgs()
    private lateinit var binding: ActivityDetailBinding
    private var isToolbarShow = false
    private var statusState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        detailComponent = (application as MyApplication).appComponent.detailComponent().create()
        detailComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail)

        binding.apply {
            rv_season_list.adapter = SeasonAdapter()
            rv_season_list.setHasFixedSize(true)
            lifecycleOwner = this@DetailActivity
            viewModel = detailViewModel
        }
        setupDetail()
        checkFavorite()
        fab_movies.setOnClickListener {
            statusState = !statusState
            setStateFavoriteMovies(statusState)

            startAnimator(fab_movies)
            if (statusState) {
                Snackbar.make(detail_layout, R.string.state_true, Snackbar.LENGTH_SHORT)
                    .show()
                insertFavoriteMovies()
            } else {
                Snackbar.make(detail_layout, R.string.state_false, Snackbar.LENGTH_SHORT)
                    .show()
                deleteFavoriteMovies()
            }
        }
        fab_shows.setOnClickListener {
            statusState = !statusState
            setStateFavoriteShows(statusState)

            startAnimator(fab_shows)
            if (statusState) {
                Snackbar.make(detail_layout, R.string.state_true, Snackbar.LENGTH_SHORT)
                    .show()
                insertFavoriteShows()
            } else {
                Snackbar.make(detail_layout, R.string.state_false, Snackbar.LENGTH_SHORT)
                    .show()
                deleteFavoriteShows()
            }
        }
    }

    private fun setupDetail() {
        args.idMovie?.let { id ->
            detailViewModel.getDetailMovie(id)
            setupMovies()
            content_shows.visibility = View.GONE
        }
        args.idShows?.let { id ->
            detailViewModel.getDetailTvShows(id)
            setupShows()
            content_movies.visibility = View.GONE
        }
    }

    private fun checkFavorite() {
        detailViewModel.detailContentMovie.observe(this, { detail ->
            val check = detail.id
            if (check != null) {
                lifecycleScope.launch {
                    val checkState = detailViewModel.checkFavoriteMovies(check)
                    setStateFavoriteMovies(checkState)
                    statusState = checkState
                }
            }
        })
        detailViewModel.detailContentShows.observe(this, { detail ->
            val check = detail.id
            if (check != null) {
                lifecycleScope.launch {
                    val checkState = detailViewModel.checkFavoriteShows(check)
                    setStateFavoriteShows(checkState)
                    statusState = checkState
                }
            }
        })
    }

    private fun insertFavoriteMovies() {
        detailViewModel.detailContentMovie.observe(this, { detail ->
            val values = FavoriteMovies(
                detail.id,
                detail.title,
                detail.genres,
                detail.popularity,
                detail.voteCount,
                detail.overview,
                detail.originalTitle,
                detail.posterPath,
                detail.backdropPath,
                detail.releaseDate,
                detail.voteAverage.toString(),
                detail.tagline,
                detail.homepage,
                detail.status
            )

            detailViewModel.insertFavoriteMovies(values)
        })
    }

    private fun deleteFavoriteMovies() {
        detailViewModel.detailContentMovie.observe(this, { detail ->
            detail.id?.let { detailViewModel.deleteFavoriteMoviesById(it) }
        })
    }

    private fun insertFavoriteShows() {
        detailViewModel.detailContentShows.observe(this, { detail ->
            val values = FavoriteShows(
                detail.id,
                detail.genres,
                detail.popularity,
                detail.voteCount,
                detail.firstAirDate,
                detail.overview,
                detail.seasons,
                detail.posterPath,
                detail.originalName,
                detail.voteAverage.toString(),
                detail.name,
                detail.tagline,
                detail.homepage,
                detail.status
            )

            detailViewModel.insertFavoriteShows(values)
        })
    }

    private fun deleteFavoriteShows() {
        detailViewModel.detailContentShows.observe(this, { detail ->
            detail.id?.let { detailViewModel.deleteFavoriteShowsById(it) }
        })
    }

    private fun setStateFavoriteMovies(checkState: Boolean) {
        if (checkState) {
            fab_movies.setImageResource(R.drawable.round_favorite_black_48dp)
        } else {
            fab_movies.setImageResource(R.drawable.round_favorite_border_black_48dp)
        }
    }

    private fun setStateFavoriteShows(checkState: Boolean) {
        if (checkState) {
            fab_shows.setImageResource(R.drawable.round_favorite_black_48dp)
        } else {
            fab_shows.setImageResource(R.drawable.round_favorite_border_black_48dp)
        }
    }

    private fun setupMovies() {
        detail_scroll_view_movies.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
                val showToolbar = scrollY > toolbar_movies.height

                if (isToolbarShow != showToolbar) {
                    isToolbarShow = showToolbar

                    appbar_movies.isActivated = showToolbar
                    toolbar_layout_movies.isTitleEnabled = showToolbar
                }
            })
        toolbar_movies.setNavigationOnClickListener {
            finish()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            detail_overview_movies.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }

        detailViewModel.detailContentMovie.observe(this, { movies ->
            toolbar_movies.setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.action_share -> {
                        if (movies != null) {
                            onShareMovies(movies)
                        }
                        true
                    }
                    else -> false
                }
            }
        })
    }

    private fun setupShows() {
        detail_scroll_view_shows.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
                val showToolbar = scrollY > toolbar_shows.height

                if (isToolbarShow != showToolbar) {
                    isToolbarShow = showToolbar

                    appbar_shows.isActivated = showToolbar
                    toolbar_layout_shows.isTitleEnabled = showToolbar
                }
            })
        toolbar_shows.setNavigationOnClickListener {
            finish()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            detail_overview_movies.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }

        detailViewModel.detailContentShows.observe(this, { shows ->
            toolbar_shows.setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.action_share -> {
                        if (shows != null) {
                            onShareShows(shows)
                        }
                        true
                    }
                    else -> false
                }
            }
        })
    }

    private fun onShareShows(shows: ResponseDetailShows) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(
            Intent.EXTRA_TEXT, resources.getString(
                R.string.intent_content,
                shows.name
            )
        )
        intent.type = INTENT_TYPE
        startActivity(Intent.createChooser(intent, INTENT_TITLE))
    }

    private fun onShareMovies(movies: ResponseDetailMovies) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(
            Intent.EXTRA_TEXT, resources.getString(
                R.string.intent_content,
                movies.title
            )
        )
        intent.type = INTENT_TYPE
        startActivity(Intent.createChooser(intent, INTENT_TITLE))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }
}