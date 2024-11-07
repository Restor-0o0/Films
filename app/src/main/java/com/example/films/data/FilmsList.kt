package com.example.films.data

import androidx.collection.ArrayMap
import androidx.collection.ArraySet

class FilmsList(films: List<Film>) {
    private lateinit var Films: List<Film>
    private lateinit var Genres: Set<String>

    init {
        addFilms(films)
    }

    fun addFilms(films: List<Film>){
        for(item in films){
            this.Films + item
            addGenres(item.genres)
        }
    }

    private fun addGenres(genres: List<String>){
        for(item in genres){
            this.Genres + item
        }
    }
    fun getFilmsByGenre(genre: String): List<Film>{
        return this.Films.filter {
            it.genres.contains(genre)
        }
    }
    fun getAllFilms(): List<Film>{ return Films }
    fun getAllGenres(): Set<String>{ return Genres }
}