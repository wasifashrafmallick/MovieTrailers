package com.example.movietrailers.data.models

import androidx.lifecycle.LiveData
import androidx.paging.PagedList


data class MovieResult(
    val  data: LiveData<PagedList<Movie>>,
    val networkErrors: LiveData<String>,
    val loadingData: LiveData<Boolean>
)