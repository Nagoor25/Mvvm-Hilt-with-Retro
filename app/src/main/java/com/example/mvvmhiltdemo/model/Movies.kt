package com.example.mvvmhiltdemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mvvmhiltdemo.db.Converters
import java.io.Serializable
@Entity(tableName = "movies")
data class Movies(
    val adult: Boolean,
    val backdrop_path: String,
    @TypeConverters(Converters::class)
    val genre_ids: List<Int>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    @PrimaryKey(autoGenerate = true)
     val id: Int?=null
):Serializable