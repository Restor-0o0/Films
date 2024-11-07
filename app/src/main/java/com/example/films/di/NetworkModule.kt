package com.example.films.di

import com.example.films.R
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module{

    val connectTimeout: Long = 20

    fun provideHttpClient(): OkHttpClient{
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout,TimeUnit.SECONDS)
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }


    fun provideRetrofit(client: OkHttpClient,url: String): Retrofit{
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }
    single{
        provideHttpClient()
    }
    single{
        val url =androidContext().getString(R.string.BASE_URL)
        provideRetrofit(get(),url)
    }



}