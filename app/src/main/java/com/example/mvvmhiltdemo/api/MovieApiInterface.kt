package com.example.mvvmhiltdemo.api

import com.example.mvvmhiltdemo.model.ServerResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {
    companion object{
        val baseURL="https://api.themoviedb.org/3/movie/"
        //https://api.themoviedb.org/3/movie/top_rated?api_key=11f9c45d0a1f3b2e8ef6b132e83dd063&language=en-US&page=1
    }
    @GET("top_rated")
    suspend fun provideTopMovies(
        @Query("api_key") api_key:String,
        @Query("language") language:String,
        @Query("page") pageNumber:Int

    ):Response<ServerResponse>
}