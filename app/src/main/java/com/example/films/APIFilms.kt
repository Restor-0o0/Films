package com.example.films

import com.example.films.data.Film
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIFilms {
    @GET("sequeniatesttask/films.json")
    suspend fun getAllFilms() : Response<List<Film>>
}