package com.example.films.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.films.R
import com.example.films.data.Film
import com.example.films.databinding.FilmDetailsFragmentBinding
import kotlin.math.round

class FilmDetailsFragment(
    val film: Film
): Fragment() {

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

            Glide
                .with(viewBinding.image.context)
                .load(film.image_url)
                .centerCrop()
                .into(viewBinding.image)
        viewBinding.title.textView.text = this.film.name
        viewBinding.name.text = this.film.localized_name
        viewBinding.genresAndYear.text = if(this.film.genres.isNotEmpty()){
            this.film.genres.joinToString(separator = ", ", postfix = ", ").plus(this.film.year.toString().plus(" ").plus(getString(R.string.year)))
        }else{
            this.film.year.toString().plus(" ").plus(getString(R.string.year))
        }

        viewBinding.rating.text = round(this.film.rating).toString()
        viewBinding.videoHosting.text = getString(R.string.videohosting)
        viewBinding.description.text = this.film.description
        viewBinding.title.backButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}