package com.example.films.reposetory

import com.example.films.data.Film
import com.example.films.data.FilmsList
import com.example.films.util.APIResult

interface FilmsReposetory {
    suspend fun getAllFilms() : APIResult<FilmsList>
}