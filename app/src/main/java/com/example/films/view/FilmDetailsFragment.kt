package com.example.films.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.films.R
import com.example.films.databinding.FilmDetailsFragmentBinding
import com.example.films.viewmodel.FilmsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.round

class FilmDetailsFragment(
): Fragment() {
    private val filmsViewModel by viewModel<FilmsViewModel>()
    private lateinit var viewBinding: FilmDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.film_details_fragment,container,false)
        viewBinding.lifecycleOwner = this
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(filmsViewModel.selectedFilmImage != null){
            viewBinding.image.setImageBitmap(filmsViewModel.selectedFilmImage)
        }
        viewBinding.title.textView.text = this.filmsViewModel.selectedFilm.name
        viewBinding.name.text = this.filmsViewModel.selectedFilm.localized_name
        viewBinding.genresAndYear.text = if(this.filmsViewModel.selectedFilm.genres.isNotEmpty()){
            this.filmsViewModel.selectedFilm.genres.joinToString(separator = ", ", postfix = ", ").plus(this.filmsViewModel.selectedFilm.year.toString().plus(" ").plus(getString(R.string.year)))
        }else{
            this.filmsViewModel.selectedFilm.year.toString().plus(" ").plus(getString(R.string.year))
        }
        viewBinding.rating.text = round(this.filmsViewModel.selectedFilm.rating).toString()
        viewBinding.videoHosting.text = getString(R.string.videohosting)
        viewBinding.description.text = this.filmsViewModel.selectedFilm.description
        viewBinding.title.backButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}