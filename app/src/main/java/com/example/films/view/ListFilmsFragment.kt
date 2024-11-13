package com.example.films.view

import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.map
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.films.R
import com.example.films.data.Film
import com.example.films.data.Genre
import com.example.films.databinding.FilmsListFragmentBinding

import com.example.films.viewmodel.FilmsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope


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
                filmsViewModel.loadAllFilms()
            }
            catch (e: Exception){
            }
        }
        if(filmsViewModel.create){
            try{
                    filmsViewModel.loadAllFilms()
            }
            catch (e: Exception){
            }
            filmsViewModel.create = filmsViewModel.create.not()
        }
        viewBinding.scrollToGenres.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                viewBinding.nestedScrollView.scrollTo(0,0)
            }

        })
        viewBinding.genreList.layoutManager =LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        viewBinding.genreList.adapter = genresAdapter
        viewBinding.genreList.isNestedScrollingEnabled = false
        viewBinding.filmsList.layoutManager= GridLayoutManager(context,2)
        viewBinding.filmsList.adapter = filmsAdapter



        viewBinding.nestedScrollView.setOnScrollChangeListener { view, i, i2, i3, i4 ->
            val genre_rect = Rect()
            val genres_list_rect = Rect()
            viewBinding.genreList.getHitRect(genres_list_rect)
            if(viewBinding.genreList.getLocalVisibleRect(genres_list_rect)){

                viewBinding.scrollToGenres.visibility = View.INVISIBLE
            }else{
                viewBinding.scrollToGenres.visibility = View.VISIBLE
            }

        }
        filmsViewModel.genresList.observe(viewLifecycleOwner,Observer{ item ->
            lifecycleScope.launch {
                if(item.isNotEmpty() && item != null){
                    genresAdapter.setGenres(item.map {
                        Genre(
                            it,
                            filmsViewModel.selectedGenres.contains(it.lowercase())
                        )
                    })
                    if(filmsViewModel.filmsList.value!!.isNotEmpty() && filmsViewModel.filmsList != null){

                        filmsAdapter.setFilmsList(filmsViewModel.getFilmsBySelectedGenres())
                    }
                    filmsViewModel.genresList.removeObservers(viewLifecycleOwner)
                }
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

        genresAdapter.onClick(filmsViewModel.selectedPositionGenre,position)
        if(filmsViewModel.selectedGenres  == genre.lowercase()){
            filmsViewModel.selectedGenres = ""
            filmsViewModel.selectedPositionGenre = null
        }
        else{
            filmsViewModel.selectedGenres = genre.lowercase()
            filmsViewModel.selectedPositionGenre = position
        }
        filmsAdapter.setFilmsList(filmsViewModel.getFilmsBySelectedGenres())
    }

    override fun reconnect(){
        try{
            filmsViewModel.loadAllFilms()
        }
        catch (e: Exception){

        }
    }


}

