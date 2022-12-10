package com.example.ricardo.moviesmanager.models.dao

import androidx.room.*
import com.example.ricardo.moviesmanager.models.Filme

@Dao
interface MovieRoomDao {
    companion object Constant {
        const val MOVIE_DATABASE_FILE = "movies_room"
        const val MOVIE_TABLE = "movie"
        const val ID_COLUMN = "id"
        const val NAME_COLUMN = "name"
    }

    @Insert
    fun createFilme(filme: Filme)

    @Query("SELECT * FROM $MOVIE_TABLE WHERE $ID_COLUMN = :id")
    fun retrieveFilme(id: Int): Filme?

    @Query("SELECT * FROM $MOVIE_TABLE ORDER BY $NAME_COLUMN")
    fun retrieveFilmes(): MutableList<Filme>

    @Update
    fun updateFilme(filme: Filme): Int

    @Delete
    fun deleteFilme(filme: Filme): Int
}