package com.example.films

import com.example.films.data.Film
import retrofit2.Response
import retrofit2.http.GET

interface APIFilms {
    @GET("")
    suspend fun getAllFilms() : Response<List<Film>>
}