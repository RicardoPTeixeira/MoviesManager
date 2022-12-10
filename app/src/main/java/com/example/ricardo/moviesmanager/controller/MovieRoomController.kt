package com.example.ricardo.moviesmanager.controller

import android.os.AsyncTask
import androidx.room.Room
import com.example.ricardo.moviesmanager.models.database.FilmeRoomDaoDatabase
import com.example.ricardo.moviesmanager.models.Filme
import com.example.ricardo.moviesmanager.models.dao.MovieRoomDao
import com.example.ricardo.moviesmanager.models.dao.MovieRoomDao.Constant.MOVIE_DATABASE_FILE
import com.example.ricardo.moviesmanager.views.MainActivity

class FilmeRoomController(private val mainActivity: MainActivity) {
    private val filmeDao: MovieRoomDao by lazy {
        Room.databaseBuilder(
            mainActivity,
            FilmeRoomDaoDatabase::class.java,
            MOVIE_DATABASE_FILE
        ).build().getFilmeRoomDao()
    }

    fun insertFilme(filme: Filme) {
        Thread {
            filmeDao.createFilme(filme)
            getFilmes()
        }.start()
    }

    fun getFilme(id: Int) = filmeDao.retrieveFilme(id)
    fun getFilmes() {
        object: AsyncTask<Unit, Unit, MutableList<Filme>>(){
            override fun doInBackground(vararg params: Unit?): MutableList<Filme> {
                val returnList = mutableListOf<Filme>()
                returnList.addAll(filmeDao.retrieveFilmes())
                return returnList
            }

            override fun onPostExecute(result: MutableList<Filme>?) {
                super.onPostExecute(result)
                if (result != null){
                    mainActivity.updateFilmeList(result)
                }
            }
        }.execute()
    }

    fun editFilme(filme: Filme) {
        Thread {
            filmeDao.updateFilme(filme)
            getFilmes()
        }.start()
    }

    fun removeFilme(filme: Filme) {
        Thread {
            filmeDao.deleteFilme(filme)
            getFilmes()
        }.start()
    }
}