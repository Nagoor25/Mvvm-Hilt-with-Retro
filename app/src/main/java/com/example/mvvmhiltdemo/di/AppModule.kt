package com.example.mvvmhiltdemo.di

import com.example.mvvmhiltdemo.api.MovieApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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



}