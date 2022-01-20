package com.example.mvvmhiltdemo.repository

import com.example.mvvmhiltdemo.Constants.apiKey
import com.example.mvvmhiltdemo.api.MovieApiInterface
import com.example.mvvmhiltdemo.db.MovieDao
import com.example.mvvmhiltdemo.model.Movies
import javax.inject.Inject

class DefMovieRepository @Inject constructor(
    val apiInterface: MovieApiInterface,
    val movieDao: MovieDao
){
   // val api=MovieApiInterface()
    suspend fun getTopRatedMovies()
    =apiInterface.provideTopMovies(apiKey,"en-US",1)

    suspend fun addMovie(movie:Movies)
    =movieDao.addMovie(movie)

    suspend fun deleteMovie(movie: Movies)
    =movieDao.deleteMovie(movie)

     fun observeAllTopMovieFromDB()
      =movieDao.observeAllTopMovies()
}