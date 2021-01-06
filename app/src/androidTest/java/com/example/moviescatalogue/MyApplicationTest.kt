package com.example.moviescatalogue

import com.example.moviescatalogue.di.AppComponent
import com.example.moviescatalogue.di.DaggerTestAppComponent

class MyApplicationTest : MyApplication() {

    override fun initializeComponent(): AppComponent {
        return DaggerTestAppComponent.factory().create(applicationContext)
    }
}