package com.example.mvvmhiltdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mvvmhiltdemo.databinding.FragmentMovieDetailBinding
import com.example.mvvmhiltdemo.repository.DefMovieRepository
import com.example.mvvmhiltdemo.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
@AndroidEntryPoint
class MovieDetailFragment :Fragment(R.layout.fragment_movie_detail) {
  /*  @Inject
    lateinit var repository:DefMovieRepository*/
  private val viewModel:MovieViewModel by viewModels()

    val args:MovieDetailFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentMovieDetailBinding.bind(view)
        val movie=args.movie

        Log.e("movie",movie.toString())
        binding.apply {
            movie?.let { movie->
                titleTv.text=movie.title
                val imageURL= Constants.imageBaseUrl +movie.poster_path
                Glide.with(this@MovieDetailFragment).load(imageURL)
                    .into(movieImageView)
                releaseDate.text=movie.release_date
                description.text=movie.overview
                rating.text=movie.vote_average.toString()

            }
            addBtn.setOnClickListener {
                viewModel.addMovie(movie)
                Snackbar.make(it,"${movie.title} is Added",Snackbar.LENGTH_SHORT).show()

/*
   lifecycleScope.launch (Dispatchers.IO){
           viewModel.addMovie(movie)
                }*/
/*
           runBlocking {
               job1.join()
               Snackbar.make(it,"Movie Added",Snackbar.LENGTH_SHORT).show()
                }
*/
          //      Snackbar.make(it,"Movie Added",Snackbar.LENGTH_SHORT).show()
            }
            clickHereSavedTv.setOnClickListener {

                findNavController().navigate(R.id.action_movieDetailFragment_to_topMoviesFragmentDB)
            }

        }
    }
}

