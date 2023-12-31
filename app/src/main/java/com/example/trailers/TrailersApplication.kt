package com.example.movietrailers

import android.app.Application
import androidx.work.*
import com.example.movietrailers.data.repository.MovieRepository
import com.example.movietrailers.worker.RefreshPopularMoviesWork
import timber.log.Timber
import java.sql.Time
import java.util.concurrent.TimeUnit


class  movietrailersApplication: Application(){

    val movieRepository: MovieRepository
        get ()= ServiceLocator.provideMoviesRepository(this)

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    private fun setUpPopularMovieManageJob(){
        val workMaConfig = Configuration.Builder()
            .setWorkerFactory(RefreshPopularMoviesWork.Factory()).build()
        WorkManager.initialize(this,workMaConfig)

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val work = PeriodicWorkRequestBuilder<RefreshPopularMoviesWork>(1, TimeUnit.DAYS)
            .setConstraints(constraints).build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(RefreshPopularMoviesWork::class.java.name,ExistingPeriodicWorkPolicy.REPLACE,work)
    }
}