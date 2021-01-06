package com.example.moviescatalogue.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviescatalogue.MyApplication
import com.example.moviescatalogue.R
import com.example.moviescatalogue.ui.main.di.MainComponent

class MainActivity : AppCompatActivity() {
    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (application as MyApplication).appComponent.mainComponent().create()
        mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}