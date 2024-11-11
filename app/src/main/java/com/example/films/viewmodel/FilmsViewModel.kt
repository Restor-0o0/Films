package com.example.films.viewmodel


import android.graphics.Bitmap
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
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
    var selectedGenres: String = ""
    var selectedPositionGenre: Int? = null
    var create:Boolean = true
    lateinit var selectedFilm:Film
    var selectedFilmImage: Bitmap? = null



    fun getAllFilms(){
        showLoading.value = true
        showError.value = null
        genresList.value = emptySet()
        viewModelScope.launch {
            val result =reposetory.getAllFilms()
            try{
                when(result){
                    is APIResult.Success->{
                        filmsList.value = result.successData.films
                        addGenresFromFilmsList(result.successData.films)
                        showLoading.value = null
                    }
                    is APIResult.Error->{
                        showLoading.value = false
                        showError.value = result.exception.message
                    }
                }
            }
            catch (e:Exception){
                showLoading.value = false
                showError.value = e.message
            }

        }
    }
    fun addGenresFromFilmsList(films: List<Film>){
        var setGenres: Set<String> = emptySet()
        for(film in films){
            for(item in film.genres){
                setGenres = setGenres.plus(item)
            }
        }


        this.genresList.value = setGenres.map{
            it.replaceFirstChar {
                it.uppercase()
            }
        }
            .sorted()
            .toSet()


    }
    fun getFilmsBySelectedGenres(): List<Film>? {
        if (selectedGenres !=""){
            return this.filmsList.value?.filter {
                it.genres.contains(selectedGenres)
            }?.toList() ?: emptyList()
        }
        else{
            return filmsList.value
        }

    }

}