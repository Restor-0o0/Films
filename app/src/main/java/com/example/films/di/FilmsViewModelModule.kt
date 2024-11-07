package com.example.films.di

import com.example.films.viewmodel.FilmsViewModel
import org.koin.dsl.module

val filmsViewmodelModule = module{
    single{
        FilmsViewModel(get())
    }
}