package com.example.movietrailers

import android.content.Context
import androidx.room.Room
import com.example.movietrailers.data.local.TrailerDatabase
import com.example.movietrailers.data.remote.NetworkApiService
import com.example.movietrailers.data.repository.MovieRepository


object ServiceLocator{

    private var database: TrailerDatabase? = null

    @Volatile
    var movieRepository: MovieRepository? = null

    fun provideMoviesRepository(context: Context): MovieRepository{
        synchronized(this){
            return movieRepository?: createMovieRepository(context)
        }
    }



    private fun createMovieRepository(context: Context):MovieRepository{
        val database = database ?: createDatabase(context)
        val newRepo = MovieRepository(NetworkApiService, database.movieDao())
        movieRepository = newRepo
        return newRepo
    }


    private fun createDatabase(context: Context): TrailerDatabase{
        val db = Room.databaseBuilder(
            context.applicationContext,
            TrailerDatabase::class.java,
            "movies.db"
        ).build()
        database = db
        return db
    }


}