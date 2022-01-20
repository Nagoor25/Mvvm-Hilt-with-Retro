package com.example.mvvmhiltdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmhiltdemo.model.Movies
import com.example.mvvmhiltdemo.repository.DefMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val repository: DefMovieRepository ):ViewModel() {
     val _response=MutableLiveData<List<Movies>>()
     val respTopRatedMovie:LiveData<List<Movies>>
     get() = _response
  /*  val _response_db=MutableLiveData<List<Movies>>()
    val respTopRatedMovie_db:LiveData<List<Movies>>
        get() = _response_db*/
 // lateinit var respTopRatedMovie_db:LiveData<List<Movies>>

    init {
        getTopRatedMovies()
     //   getAllTopMoviesFromDB()

    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
      repository.getTopRatedMovies().let { response ->
          if (response.isSuccessful){
              _response.postValue(response.body()?.results)
          }else{
          Log.e("eee","getTopRatedMovies${response.code()}")
          }
      }

        }
    }
    fun addMovie(movie: Movies)=
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMovie(movie)

        }
    fun deleteMovie(movie: Movies)=
        viewModelScope.launch {
            repository.deleteMovie(movie)
        }
    fun getAllTopMoviesFromDB()=
  //      viewModelScope.launch (Dispatchers.IO){
         repository.observeAllTopMovieFromDB()
          //  val va=list.value
            //_response_db.postValue(list.value)

          //  respTopRatedMovie_db=list

        //   Log.e("eee","getTopRatedMoviesDB${respTopRatedMovie_db.value.toString()}")




}



