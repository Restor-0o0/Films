package com.example.films.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.films.data.Film
import com.example.films.reposetory.FilmsReposetory
import com.example.films.util.APIResult
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val reposetory: FilmsReposetory
): ViewModel() {

    val showLoading = MutableLiveData<Boolean?>()
    val filmsList = MutableLiveData<List<Film>>()
    val genresList = MutableLiveData<Set<String>>()
    val showError = MutableLiveData<String?>()

    fun getAllFilms(){
        showLoading.value = true
        showError.value = null
        genresList.value = emptySet()
        viewModelScope.launch {
            val result =reposetory.getAllFilms()

            when(result){
                is APIResult.Success->{
                    filmsList.value = result.successData.films
                    Log.e("DEUUGGg",filmsList.value?.size.toString())
                    addGenresFromFilmsList(result.successData.films)
                    showLoading.value = null
                }
                is APIResult.Error->{
                    showLoading.value = false
                    showError.value = result.exception.message
                }
            }
        }
    }
    fun addGenresFromFilmsList(films: List<Film>){
        var setGenres: Set<String> = emptySet()
        for(film in films){
            //Log.e("DEUUGG",film.genres.toString())
            for(item in film.genres){
                //Log.e("DEUUGG",item)
                setGenres = setGenres.plus(item)
            }
        }
        this.genresList.value = setGenres
        Log.e("DEUUGG",setGenres.size.toString())
        Log.e("DEUUGG",genresList.value?.size.toString())


    }
    fun getFilmsByGenre(genre: List<String>): List<Film>{
        return this.filmsList.value?.filter {
            it.genres.containsAll(genre)
        }?.toList() ?: emptyList()
    }
}