package com.example.films.di

import com.example.films.APIFilms
import org.koin.dsl.module
import retrofit2.Retrofit


val apiModule = module{
    fun provideAPIFilms(retrofit: Retrofit): APIFilms{
        return retrofit.create(APIFilms::class.java)
    }
    single{
provideAPIFilms(get())
    }
}