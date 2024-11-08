package com.example.films.util

import com.example.films.data.FilmsList
import java.lang.Exception

sealed class APIResult<out T> {
    data class Success<out T>(val successData: T):APIResult<T>()
    class Error(val exception: Exception, val message: String = exception.localizedMessage.toString()) : APIResult<Nothing>()
}