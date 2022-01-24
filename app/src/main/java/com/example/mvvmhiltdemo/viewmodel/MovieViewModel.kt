package com.example.mvvmhiltdemo.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmhiltdemo.MvvmHiltApplication
import com.example.mvvmhiltdemo.Resource
import com.example.mvvmhiltdemo.model.Movies
import com.example.mvvmhiltdemo.model.ServerResponse
import com.example.mvvmhiltdemo.repository.DefMovieRepository
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val repository: DefMovieRepository ):ViewModel() {
    /* val _response=MutableLiveData<List<Movies>>()
     val respTopRatedMovie:LiveData<List<Movies>>
     get() = _response*/

   val _response=MutableLiveData<Resource<ServerResponse>>()
    val respTopRatedMovie:LiveData<Resource<ServerResponse>>
    get() = _response

    init {
        getTopRatedMovies()
     //   getAllTopMoviesFromDB()

    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
        _response.postValue(Resource.Loading())
      val res= repository.getTopRatedMovies()
          /*if (response.isSuccessful){
              _response.postValue(response.body())

          }else{

          Log.e("eee","getTopRatedMovies${response.code()}")
          }

*/
         res?.let {
             _response.postValue(handlingServerResponse(it))
         }


        }
    }
    fun handlingServerResponse(response: Response<ServerResponse>):Resource<ServerResponse>{
     if (response.isSuccessful){
         response.body()?.let {
             return Resource.Success(it)
         }

     }
        return Resource.Error(response.message())
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



/*
    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<MvvmHiltApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
*/
}



