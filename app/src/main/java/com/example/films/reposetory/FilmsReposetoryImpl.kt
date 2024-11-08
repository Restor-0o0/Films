package com.example.films.reposetory

import android.content.Context
import android.util.Log
import com.example.films.APIFilms
import com.example.films.data.Film
import com.example.films.data.FilmsList
import com.example.films.util.APIResult
import com.example.films.util.NetworkManager
import com.example.films.util.Utils
import com.example.films.util.noNetworkConnectivityError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import kotlin.Exception

class FilmsReposetoryImpl(
    val context: Context,
    val api:APIFilms
) : FilmsReposetory {
    override suspend fun getAllFilms(): APIResult<FilmsList> {
        Log.e("DEUUG","tyt")
        if (NetworkManager.isOnline(context)){
            Log.e("DEUUG","in")
            return try{
                val response: Response<FilmsList> = this.api.getAllFilms()

                if(response.isSuccessful){
                    Utils.handleApiSuccess(response)
                }
                else{
                    Utils.handleApiError(response)
                }
            }catch (e: Exception){
                Log.e("DEUUG",e.toString())
                APIResult.Error(e)
            }
        }
        else{
            APIResult.Error(Exception("Ошибка подключения сети"))
            return context.noNetworkConnectivityError()
        }
    }

}