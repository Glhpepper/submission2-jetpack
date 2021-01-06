package com.example.moviescatalogue.data.local.room

import androidx.room.TypeConverter
import com.example.moviescatalogue.data.remote.response.GenresItemMovies
import com.example.moviescatalogue.data.remote.response.GenresItemShows
import com.example.moviescatalogue.data.remote.response.SeasonsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converter {
    @TypeConverter
    fun fromGenreMovies(value: String): List<GenresItemMovies> {
        val type = object : TypeToken<List<GenresItemMovies>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromListGenreMovies(list: List<GenresItemMovies>): String = Gson().toJson(list)

    @TypeConverter
    fun fromStringGenreShows(value: String): List<GenresItemShows> {
        val type = object : TypeToken<List<GenresItemShows>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromListGenreShows(list: List<GenresItemShows>): String = Gson().toJson(list)


    @TypeConverter
    fun fromStringSeasonItem(value: String): List<SeasonsItem> {
        val type = object : TypeToken<List<SeasonsItem>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromListSeasonItem(list: List<SeasonsItem>): String = Gson().toJson(list)
}