package com.example.films.view

import android.icu.text.Transliterator.Position

interface GenreClickListener {
    fun onGenreClick(position: Int,genre:String)
}