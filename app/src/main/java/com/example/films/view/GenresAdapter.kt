package com.example.films.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.films.R
import com.example.films.databinding.GenresItemBinding
import retrofit2.http.POST

class GenresAdapter(
    val context: Context?,
    val clickListener: GenreClickListener
): RecyclerView.Adapter<GenresAdapter.GenreViewHolder>()  {


    private var genreList: List<String> = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val viewBinding: GenresItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.genres_item,parent,false)
        return GenreViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    fun setGenres(genres: List<String>){
        this.genreList = genres
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class GenreViewHolder(
        val viewBinding: GenresItemBinding
    ): RecyclerView.ViewHolder(viewBinding.root){

        fun bind(position:Int){
            viewBinding.genre = genreList[position]
            viewBinding.clickListener = clickListener
        }
    }
}