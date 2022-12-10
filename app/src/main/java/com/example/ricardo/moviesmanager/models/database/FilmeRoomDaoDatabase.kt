package com.example.ricardo.moviesmanager.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ricardo.moviesmanager.models.Filme
import com.example.ricardo.moviesmanager.models.dao.MovieRoomDao

@Database(entities = [Filme::class], version = 1)
abstract class FilmeRoomDaoDatabase: RoomDatabase() {
    abstract fun getFilmeRoomDao(): MovieRoomDao
}