package com.example.moviescatalogue

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import com.example.moviescatalogue.di.AppComponent
import com.example.moviescatalogue.di.DaggerAppComponent

open class MyApplication : Application() {

    val appComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}