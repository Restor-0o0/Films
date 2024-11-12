package com.example.films.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.films.R
import com.example.films.data.Film
import com.example.films.databinding.FilmItemBinding
import com.example.films.viewmodel.FilmsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

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

    fun setFilmsList(films: List<Film>?){
        films?.let {
            this.filmsList = films
            notifyDataSetChanged()
        }

    }
    inner class FilmsViewHolder(
        private val viewBinding: FilmItemBinding
    ): RecyclerView.ViewHolder(viewBinding.root){
        fun bind(position: Int){
            val item = filmsList[position]
            viewBinding.film = item
            viewBinding.clickListener = clickListener
            try{

                Glide
                    .with(viewBinding.image.context)
                    .load(item.image_url)
                    .error(R.drawable.glide_error)
                    .into(viewBinding.image)
                    /*if(filmsList[position].image != null) {
                        Log.e("Glide","storage")
                        viewBinding.image.setImageBitmap(filmsList[position].image)
                    }
                    else{
                        Log.e("Glide","load")
                        filmsList[position].image = context?.getDrawable(R.drawable.glide_error)?.toBitmap()
                        Glide
                            .with(viewBinding.image.context)
                            .asBitmap()
                            .load(item.image_url)
                            .listener(object: RequestListener<Bitmap> {
                                override fun onLoadFailed(
                                    e: GlideException?,
                                    model: Any?,
                                    target: Target<Bitmap>?,
                                    isFirstResource: Boolean
                                ): Boolean {

                                    return false
                                }

                                override fun onResourceReady(
                                    resource: Bitmap?,
                                    model: Any?,
                                    target: Target<Bitmap>?,
                                    dataSource: DataSource?,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    viewBinding.image.setImageBitmap(resource)
                                    resource?.let{
                                        filmsList[position].image = it
                                    }
                                    return true
                                }

                            })
                            .error(R.drawable.glide_error)
                            .into(viewBinding.image)

                    }*/




                viewBinding.image.clipToOutline = true
            }catch (e:Exception){
                Log.e("GlideError",e.message.toString())
            }

        }
    }
}