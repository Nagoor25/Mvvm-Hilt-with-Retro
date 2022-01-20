package com.example.mvvmhiltdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmhiltdemo.Constants.imageBaseUrl
import com.example.mvvmhiltdemo.databinding.MovieRowBinding
import com.example.mvvmhiltdemo.model.Movies
import javax.inject.Inject

class MovieAdapter @Inject constructor(): RecyclerView.Adapter<MovieAdapter.MyHolder>() {

       val differCallBack=object : DiffUtil.ItemCallback<Movies>() {
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return    oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies)=
            oldItem==newItem

    }
    val differ= AsyncListDiffer(this,differCallBack )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding=MovieRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyHolder(binding)
    }

   private var onMyItemClickListener:((Movies)->Unit)?=null

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
     val movie =differ.currentList.get(position)
        holder.binding(movie)

    }

    override fun getItemCount(): Int {
      return differ.currentList.size
    }

    inner class MyHolder(val binding: MovieRowBinding) :RecyclerView.ViewHolder(binding.root) {
        fun binding(movie: Movies?) {
            movie?.let { res->
                binding.apply {
                    titleTv.text=res.original_title
                    releaseDate.text=res.release_date

                        val imageURL=imageBaseUrl+res.poster_path
                        Glide.with(itemView.context)
                            .load(imageURL)
                            .into(movieIv);
//
                    root.setOnClickListener {
                        onMyItemClickListener?.let { it1 -> it1(movie) }
                    }
                }


            }

        }

    }
    fun setOnMyItemClickListener(listener: (Movies) -> Unit) {
        onMyItemClickListener = listener
    }

}

