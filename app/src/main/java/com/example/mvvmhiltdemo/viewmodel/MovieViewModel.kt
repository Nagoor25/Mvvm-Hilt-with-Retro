package com.example.mvvmhiltdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmhiltdemo.model.Result
import com.example.mvvmhiltdemo.model.ServerResponse
import com.example.mvvmhiltdemo.repository.DefMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val repository: DefMovieRepository ):ViewModel() {
    private val _response=MutableLiveData<List<Result>>()
     val respTopRatedMovie:LiveData<List<Result>>
    get() = _response
    init {
        getTopRatedMovies()
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
}



