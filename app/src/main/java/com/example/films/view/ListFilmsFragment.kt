package com.example.films.view

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.films.R
import com.example.films.data.Film
import com.example.films.data.Genre
import com.example.films.databinding.FilmsListFragmentBinding

import com.example.films.viewmodel.FilmsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListFilmsFragment : Fragment(),FilmClickListener,GenreClickListener,ReconnectCallListener {

    private val filmsViewModel by viewModel<FilmsViewModel>()
    private lateinit var filmsAdapter : FilmsAdapter
    private lateinit var genresAdapter: GenresAdapter
    private lateinit var viewBinding: FilmsListFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater,R.layout.films_list_fragment,container,false)
        viewBinding.lifecycleOwner = this
        val rootView = viewBinding.root
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmsAdapter = FilmsAdapter(context,this)
        genresAdapter = GenresAdapter(context,this)
        viewBinding.viewModel = filmsViewModel
        viewBinding.reconnectButton.setOnClickListener {
            try{
                filmsViewModel.getAllFilms()
            }
            catch (e: Exception){

            }

        }
        if(filmsViewModel.create){
            try{
                filmsViewModel.getAllFilms()
            }
            catch (e: Exception){

            }
            filmsViewModel.create = filmsViewModel.create.not()
        }
        viewBinding.genreList.layoutManager =LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        viewBinding.genreList.adapter = genresAdapter
        viewBinding.genreList.isNestedScrollingEnabled = false
        viewBinding.filmsList.isNestedScrollingEnabled = false
        viewBinding.filmsList.layoutManager= GridLayoutManager(context,2)
        viewBinding.filmsList.adapter = filmsAdapter


        filmsViewModel.filmsList.observe(viewLifecycleOwner, Observer {

            if(it.isNotEmpty() && it != null){
                if(filmsViewModel.selectedGenres.size  > 0 )
                {
                    filmsAdapter.setFilmsList(filmsViewModel.getFilmsBySelectedGenres())
                }
                else{
                    filmsAdapter.setFilmsList(it)
                }

            }
        })
        filmsViewModel.genresList.observe(viewLifecycleOwner,Observer{ item ->
            if(item.isNotEmpty() && item != null){
                genresAdapter.setGenres(item.map {
                    Genre(
                        it,
                        filmsViewModel.selectedGenres.contains(it)
                        )
                })

            }
        })

    }



    override fun onFilmClick(film: Film,imageBitmap:ImageView) {
        //Log.e("DEBUUUGGG","inn")

        filmsViewModel.selectedFilm = film
        if(imageBitmap.drawable != null){
            filmsViewModel.selectedFilmImage = imageBitmap.drawable!!.toBitmap()
        }


            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FilmDetailsFragment())
                .addToBackStack(null)
                .commit()
    }

    override fun onGenreClick(position: Int,genre:String) {

        if(filmsViewModel.selectedGenres.contains(genre)){
            filmsViewModel.selectedGenres = filmsViewModel.selectedGenres - genre
        }else {
            filmsViewModel.selectedGenres = filmsViewModel.selectedGenres + genre
        }
        //Log.e("DEUUGG",)
        filmsAdapter.setFilmsList(filmsViewModel.getFilmsBySelectedGenres())
        genresAdapter.onClick(position)
    }

    override fun reconnect(){
        //Log.e("DEBUUG","in")
        try{
            filmsViewModel.getAllFilms()
        }
        catch (e: Exception){

        }
    }

}