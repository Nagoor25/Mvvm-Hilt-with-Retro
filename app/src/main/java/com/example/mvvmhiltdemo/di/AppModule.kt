package com.example.mvvmhiltdemo.di

import android.content.Context
import androidx.room.Room
import com.example.mvvmhiltdemo.Constants.DB_Name
import com.example.mvvmhiltdemo.api.MovieApiInterface
import com.example.mvvmhiltdemo.db.MovieDao
import com.example.mvvmhiltdemo.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMovieApi(okHttpClient: OkHttpClient):MovieApiInterface=
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MovieApiInterface.baseURL)
            .client(okHttpClient)
            .build()
            .create(MovieApiInterface::class.java)

    @Singleton
    @Provides
     fun provideInterceptor():HttpLoggingInterceptor=
               HttpLoggingInterceptor()
             .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
     fun provideClient(interceptor: HttpLoggingInterceptor):OkHttpClient=
                 OkHttpClient().newBuilder()
                     .addInterceptor(interceptor)
                     .build()

    @Singleton
    @Provides
    fun provideDao(database: MovieDatabase):MovieDao
       = database.getMovieDao()

    @Singleton
    @Provides
    fun ProvideMovieDatabase(@ApplicationContext context:Context)=
        Room.databaseBuilder(context,MovieDatabase::class.java,DB_Name)
            .build()
}