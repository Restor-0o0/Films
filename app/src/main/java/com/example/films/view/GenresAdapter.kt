package com.example.films.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.films.R
import com.example.films.data.Genre
import com.example.films.databinding.GenresItemBinding

class GenresAdapter(
    val context: Context?,
    val clickListener: GenreClickListener
): RecyclerView.Adapter<GenresAdapter.GenreViewHolder>()  {


    private var genreList: List<Genre> = ArrayList<Genre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val viewBinding: GenresItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.genres_item,parent,false)
        return GenreViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    fun setGenres(genres: List<Genre>){
        this.genreList = genres
        notifyDataSetChanged()
    }
    fun onClick(positionOld:Int?,positionNew:Int){
        positionOld?.let {
            genreList[positionOld].active = genreList[positionOld].active.not()
        }
        genreList[positionNew].active = genreList[positionNew].active.not()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(position)
        if(genreList[position].active){
            context?.let { holder.viewBinding.name.setBackgroundColor(ContextCompat.getColor(it,R.color.orange)) }
        }
        else{
            context?.let { holder.viewBinding.name.setBackgroundColor(ContextCompat.getColor(it,R.color.white)) }
        }
    }


    inner class GenreViewHolder(
        val viewBinding: GenresItemBinding
    ): RecyclerView.ViewHolder(viewBinding.root){


        fun bind(position:Int){
            viewBinding.genre = genreList[position].name
            viewBinding.position = position
            viewBinding.clickListener = clickListener
        }
    }
}