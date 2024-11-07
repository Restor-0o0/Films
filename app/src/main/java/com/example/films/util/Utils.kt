package com.example.films.util

import com.example.films.data.Film
import com.example.films.data.FilmsList
import retrofit2.Response

object Utils {
    fun  handleApiError(response: Response<List<Film>>): APIResult.Error{
        val error = ParseAPIError.parseError(response)
        return APIResult.Error(Exception(error.message))
    }
    fun <T:Any>  handleApiSuccess(response: Response<List<Film>>): APIResult<FilmsList>{
        response.body()?.let{
            return APIResult.Success(FilmsList(it))
        } ?: return handleApiError(response)
    }
}