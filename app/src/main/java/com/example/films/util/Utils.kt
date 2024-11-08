package com.example.films.util

import com.example.films.data.Film
import com.example.films.data.FilmsList
import retrofit2.Response

object Utils {
    fun <T:Any> handleApiError(response: Response<T>): APIResult.Error{
        val error = ParseAPIError.parseError(response)
        return APIResult.Error(Exception(error.message))
    }
    fun <T:Any>  handleApiSuccess(response: Response<T>): APIResult<T>{
        response.body()?.let{
            return APIResult.Success(it)
        } ?: return handleApiError(response)
    }


}