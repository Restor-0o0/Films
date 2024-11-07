package com.example.films.data

class FilmsList(films: List<Film>) {
    private lateinit var Films: List<Film>
    private lateinit var genres: Set<String>

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
            this.genres + item
        }
    }

    fun getAllFilms(): List<Film>{ return Films }
    fun getAllGenres(): Set<String>{ return genres }
}