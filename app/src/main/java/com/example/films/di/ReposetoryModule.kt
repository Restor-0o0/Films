package com.example.films.di

import android.content.Context
import com.example.films.APIFilms
import com.example.films.reposetory.FilmsReposetory
import com.example.films.reposetory.FilmsReposetoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val reposetoryModule = module{
    fun provideFilmsReposetory(api: APIFilms,context: Context):FilmsReposetory{
        return FilmsReposetoryImpl(context,api)
    }
    single{
        provideFilmsReposetory(get(),androidContext())
    }
}