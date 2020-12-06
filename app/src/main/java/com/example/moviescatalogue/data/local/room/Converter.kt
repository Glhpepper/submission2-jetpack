package com.example.moviescatalogue.data.local.room

import androidx.room.TypeConverter
import com.example.moviescatalogue.data.remote.response.GenresItemMovies
import com.example.moviescatalogue.data.remote.response.GenresItemShows
import com.example.moviescatalogue.data.remote.response.SeasonsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converter {
    @TypeConverter
    fun fromString(value: String): ArrayList<String> {
        val type = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: ArrayList<String>): String = Gson().toJson(list)
}