package com.example.mvvmhiltdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvmhiltdemo.model.Movies

@Database(entities = [Movies::class],version = 1)
@TypeConverters(Converters::class)
abstract class MovieDatabase:RoomDatabase() {
    abstract fun getMovieDao():MovieDao
}