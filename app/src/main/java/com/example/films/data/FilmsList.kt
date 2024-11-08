package com.example.films.data

import com.google.gson.annotations.SerializedName

class FilmsList {
    @SerializedName("films")
    public lateinit var films: List<Film>

}