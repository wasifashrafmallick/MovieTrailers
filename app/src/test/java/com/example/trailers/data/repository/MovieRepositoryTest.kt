package com.example.trailers.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movietrailers.data.local.MovieDao
import com.example.movietrailers.data.models.Movie
import com.example.movietrailers.data.models.MovieType
import com.example.movietrailers.data.models.ResultResponse
import com.example.movietrailers.data.remote.NetworkApiService
import com.example.movietrailers.data.repository.MovieRepository
import com.example.trailers.MainCoroutineRule
import com.example.trailers.data.remote.MovieApiService
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class MovieRepositoryTest {
    @Mock
    private lateinit var service: MovieApiService;
    private lateinit var ioDispatcher: CoroutineDispatcher
    private lateinit var movieRepository: MovieRepository

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkService: NetworkApiService

    @Mock
    private lateinit var movieDao: MovieDao

    private val deserializer =
        ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(
                KotlinModule.Builder().withReflectionCacheSize(512)
                    .configure(KotlinFeature.NullToEmptyCollection, false)
                    .configure(KotlinFeature.NullToEmptyMap, false)
                    .configure(KotlinFeature.NullIsSameAsDefault, false)
                    .configure(KotlinFeature.SingletonSupport, false)
                    .configure(KotlinFeature.StrictNullChecks, false).build()
            )!!

    @Before
    fun initService() {
        MockitoAnnotations.openMocks(this)
        movieRepository = MovieRepository(networkService, movieDao)
    }

    private fun fetchMovies(): ResultResponse<Movie> {
        return deserializer.readValue(javaClass.classLoader?.getResource("test_movie.json")!!)
    }

    @Test
    fun testMovieFetched() = runBlockingTest {
        Mockito.`when`(service.getMovies("en",1)).thenReturn(Response.success(fetchMovies()))
        val result = movieRepository.getMovieFromType(MovieType.Popular)
    }

    fun getMovieById() {
    }


    fun searchMovie() {
    }


    fun getMovieFromType() {
    }


    fun refreshPopularMovies() {
    }


    fun getMovieVideo() {
    }


    fun getNetwork() {
    }


    fun getMovieDao() {
    }
}