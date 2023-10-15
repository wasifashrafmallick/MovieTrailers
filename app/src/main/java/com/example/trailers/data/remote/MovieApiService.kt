package com.example.trailers.data.remote

import com.example.movietrailers.BuildConfig
import com.example.movietrailers.data.models.Movie
import com.example.movietrailers.data.models.MovieVideoResponse
import com.example.movietrailers.data.models.ResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val apiKey: String = BuildConfig.API_KEY

interface MovieApiService {

    @GET("movie/popular?")
    suspend fun getMovies(
        @Query("language") language: String,
        @Query("page") page: Int = 1
    ): Response<ResultResponse<Movie>>

    @GET("search/movie?api_key=$apiKey")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("include_adult") includeAdult: Boolean = false
    ): Response<ResultResponse<Movie>>

    @GET("movie/{movie_id}/videos?api_key=$apiKey")
    suspend fun getMovieVideo(@Path("movie_id") movieId: Int): Response<MovieVideoResponse>
}