package com.example.movietrailers.data.remote

import com.example.movietrailers.BuildConfig
import com.example.trailers.data.remote.BaseUrlInterceptor
import com.example.trailers.data.remote.MovieApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkApiService {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL: String = "https://image.tmdb.org/t/p/w185"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttpClient())
        .build()

    val movieApiService = retrofit.create(MovieApiService::class.java)
    fun provideOkHttpClient(): OkHttpClient? {
        val httpClientBuilder = OkHttpClient()
            .newBuilder()
            .addInterceptor(BaseUrlInterceptor())
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(BODY)
            httpClientBuilder.addInterceptor(logging)
        }
        return httpClientBuilder.build()
    }
}