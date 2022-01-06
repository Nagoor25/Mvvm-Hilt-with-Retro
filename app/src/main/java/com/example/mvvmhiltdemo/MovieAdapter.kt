package com.example.mvvmhiltdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmhiltdemo.Constants.imageBaseUrl
import com.example.mvvmhiltdemo.databinding.MovieRowBinding
import com.example.mvvmhiltdemo.model.Result
import javax.inject.Inject

class MovieAdapter @Inject constructor(): RecyclerView.Adapter<MovieAdapter.MyHolder>() {

       val differCallBack=object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return    oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result)=
            oldItem==newItem

    }
    val differ= AsyncListDiffer(this,differCallBack )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding=MovieRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
     val movie =differ.currentList.get(position)
        holder.binding(movie)
    }

    override fun getItemCount(): Int {
      return differ.currentList.size
    }

    inner class MyHolder(val binding: MovieRowBinding) :RecyclerView.ViewHolder(binding.root) {
        fun binding(movie: Result?) {
            movie?.let { res->
                binding.apply {
                    titleTv.text=res.original_title
                    releaseDate.text=res.release_date


                        val imageURL=imageBaseUrl+res.poster_path
                        Glide.with(itemView.context)
                            .load(imageURL)
                            .into(movieIv);

                }
            }

        }

    }
  }

