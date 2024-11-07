package com.example.films.viewmodel


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

    val showLoading = MutableLiveData<Boolean>()
    val filmsList = MutableLiveData<List<Film>>()
    val genresList = MutableLiveData<Set<String>>()
    val showError = MutableLiveData<String?>()

    fun getAllFilms(){
        showLoading.value = true
        viewModelScope.launch {
            val result =reposetory.getAllFilms()

            showLoading.value = false
            when(result){
                is APIResult.Success->{
                    filmsList.value = result.successData.getAllFilms()
                    genresList.value = result.successData.getAllGenres()
                    showError.value = null
                }
                is APIResult.Error->{
                    showError.value = result.exception.message
                }
            }
        }
    }
}