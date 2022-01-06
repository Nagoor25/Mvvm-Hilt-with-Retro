package com.example.mvvmhiltdemo.repository

import androidx.lifecycle.LiveData
import com.example.mvvmhiltdemo.Constants.apiKey
import com.example.mvvmhiltdemo.api.MovieApiInterface
import javax.inject.Inject

class DefMovieRepository @Inject constructor(
    val apiInterface: MovieApiInterface
){
   // val api=MovieApiInterface()
    suspend fun getTopRatedMovies()=apiInterface.provideTopMovies(apiKey,"en-US",1)
}