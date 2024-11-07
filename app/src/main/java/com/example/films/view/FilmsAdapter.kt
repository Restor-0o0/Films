package com.example.films.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.films.R
import com.example.films.data.Film
import com.example.films.databinding.FilmItemBinding

class FilmsAdapter(
    val context: Context?,
    val clickListener: FilmClickListener
): RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder>() {

    private var filmsList:List<Film> = ArrayList<Film>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val viewBinding: FilmItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.film_item,parent,false)
        return FilmsViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return filmsList.size
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setFilmsList(films: List<Film>){
        this.filmsList = films
        notifyDataSetChanged()
    }
    inner class FilmsViewHolder(
        private val viewBinding: FilmItemBinding
    ): RecyclerView.ViewHolder(viewBinding.root){
        fun bind(position: Int){
            val item = filmsList[position]
            viewBinding.films = item
            viewBinding.clickListener = clickListener
        }
    }
}