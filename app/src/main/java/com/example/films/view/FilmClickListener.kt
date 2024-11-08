package com.example.films.view

import android.graphics.Bitmap
import android.widget.ImageView
import com.example.films.data.Film

interface FilmClickListener {
    fun onFilmClick(film: Film, imageBitmap: ImageView)
}