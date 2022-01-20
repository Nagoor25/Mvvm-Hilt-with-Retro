package com.example.mvvmhiltdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmhiltdemo.model.Movies

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: Movies)
    @Delete
    suspend fun deleteMovie(movie: Movies)
    @Query("Select * from  movies")
    fun observeAllTopMovies():LiveData<List<Movies>>
}