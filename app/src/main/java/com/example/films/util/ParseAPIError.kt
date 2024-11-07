package com.example.films.util

import android.content.ContentValues.TAG
import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Response
import java.lang.Exception

object ParseAPIError {
    fun parseError(response: Response<*>) :APIError{
        val gson = GsonBuilder().create()
        val error: APIError
        try{
            error = gson.fromJson(response.body()?.toString(), APIError::class.java)
        }catch (e: Exception){
            e.message?.let{ Log.d(TAG,it) }
            return APIError()
        }
        return error
    }
}