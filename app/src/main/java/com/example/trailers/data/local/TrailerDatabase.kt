package com.example.movietrailers.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.movietrailers.data.models.Movie


@Database(entities = [Movie::class], version = 1 ,exportSchema = false)
@TypeConverters(GenreIdsConverter::class)
abstract class TrailerDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao
}