package com.example.movietrailers.worker

import android.app.NotificationManager
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.movietrailers.R
import com.example.movietrailers.movietrailersApplication
import com.example.movietrailers.data.models.MovieRefreshError
import com.example.movietrailers.data.remote.NetworkApiService
import com.example.movietrailers.data.repository.MovieRepository
import com.example.movietrailers.utils.sendNotification

class RefreshPopularMoviesWork(val context: Context,params: WorkerParameters, private val movieRepository: MovieRepository):CoroutineWorker(context,params){

    private  val notificationManager = ContextCompat.getSystemService(
        context,
        NotificationManager::class.java
    ) as NotificationManager

    override suspend fun doWork(): Result {

        return  try {
            movieRepository.refreshPopularMovies()

            notificationManager.sendNotification(
                context.getText(R.string.movie_fresh_content).toString(),
                context
            )
            Result.success()
        }catch ( error: MovieRefreshError){
            Result.failure()
        }
    }

    class Factory():WorkerFactory(){
        override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters
        ): ListenableWorker? {
            val movieRepository = (appContext as movietrailersApplication).movieRepository
            return RefreshPopularMoviesWork(appContext,workerParameters,movieRepository)
        }
    }
}