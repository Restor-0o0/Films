package com.example.films.view

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.films.R
import com.example.films.databinding.FilmsListFragmentBinding

import com.example.films.viewmodel.FilmsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListFilmsFragment : Fragment(),FilmClickListener,GenreClickListener {

    private val FilmsViewModel by viewModel<FilmsViewModel>()
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
        viewBinding.genreList.layoutManager =LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        viewBinding.genreList.adapter = genresAdapter

        viewBinding.filmsList.layoutManager= GridLayoutManager(context,2)
        viewBinding.filmsList.adapter = filmsAdapter
    }


    override fun onFilmClick() {
        TODO("Not yet implemented")
    }

    override fun onGenreClick() {
        TODO("Not yet implemented")
    }

}