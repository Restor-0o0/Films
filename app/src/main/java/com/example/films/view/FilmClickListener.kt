package com.example.films.view

import com.example.films.data.Film

interface FilmClickListener {
    fun onFilmClick(film: Film)
}