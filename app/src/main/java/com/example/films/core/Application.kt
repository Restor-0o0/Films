package com.example.films.core

import android.app.Application
import com.example.films.di.apiModule
import com.example.films.di.filmsViewmodelModule
import com.example.films.di.networkModule
import com.example.films.di.reposetoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@Application)
            modules(
                apiModule,
                filmsViewmodelModule,
                reposetoryModule,
                networkModule
            )
        }
    }
}