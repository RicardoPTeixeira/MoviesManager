package com.example.ricardo.moviesmanager.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ricardo.moviesmanager.adapter.MoviesAdapter
import com.example.ricardo.moviesmanager.controller.FilmeRoomController
import com.example.ricardo.moviesmanager.controller.OrdenadorFilme
import com.example.ricardo.moviesmanager.databinding.ActivityMainBinding
import com.example.ricardo.moviesmanager.models.Filme
import com.example.ricardo.moviesmanager.utils.Constants
import com.example.ricardo.moviesmanager.utils.Modes

class MainActivity : AppCompatActivity(), OnMovieClickListener {

    private lateinit var movieARL: ActivityResultLauncher<Intent>
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private val moviesList: MutableList<Filme> = mutableListOf()
    private val ordenadorFilme : OrdenadorFilme = OrdenadorFilme()
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val filmeController: FilmeRoomController by lazy {
        FilmeRoomController(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        val f = Filme(0,"Avengers",2012,"Marvel",true,10,7,"Romance")

        layoutManager = LinearLayoutManager(this)
        amb.movieListRv.layoutManager = layoutManager

        moviesAdapter = MoviesAdapter(moviesList, this)
        amb.movieListRv.adapter = moviesAdapter

        movieARL = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if(result.resultCode != RESULT_OK) return@registerForActivityResult
            if (result.resultCode == RESULT_OK) {
                val filme = result.data?.getParcelableExtra<Filme>(Constants.MOVIE)
                filme?.let { _filme->
                    if (_filme.id != null) {
                        val position = moviesList.indexOfFirst { it.id == _filme.id }
                        if (position != -1) {
                            filmeController.editFilme(_filme)
                        }
                    }
                    else {
                        filmeController.insertFilme(_filme)
                    }
                }
            }


        }
        amb.addBtn.setOnClickListener{
            val intent = Intent(Constants.MOVIE_DATA_ACTIVITY)
            val bundle = Bundle()
            bundle.putParcelable(Constants.MODE, Modes.INSERT)
            intent.putExtras(bundle)
            movieARL.launch(intent)
        }

        amb.orderByNameBt.setOnClickListener{
            updateFilmeList(ordenadorFilme.ordenarPorNome(moviesList.toMutableList()))
        }
        amb.orderByNotaBt.setOnClickListener{
            updateFilmeList(ordenadorFilme.ordenarPorNota(moviesList.toMutableList()))
        }

        filmeController.getFilmes()
    }

    override fun onMovieClick(filme: Filme) {
        val intent = Intent(Constants.MOVIE_DATA_ACTIVITY)
        val bundle = Bundle()
        bundle.putParcelable(Constants.MODE, Modes.VIEW_OR_UPDATE)
        bundle.putParcelable(Constants.MOVIE, filme)
        intent.putExtras(bundle)
        movieARL.launch(intent)
    }

    override fun onMovieRemove(filme: Filme) {
        filmeController.removeFilme(filme)
    }

    fun updateFilmeList(_filmeList: MutableList<Filme>) {
        moviesList.clear()
        moviesList.addAll(_filmeList)
        moviesAdapter.notifyDataSetChanged()
        val hideOrderButtons = moviesList.size < 2
        if(hideOrderButtons){
            amb.orderByNotaBt.visibility = View.GONE
            amb.orderByNameBt.visibility = View.GONE
        }else{
            amb.orderByNotaBt.visibility = View.VISIBLE
            amb.orderByNameBt.visibility = View.VISIBLE
        }
    }
}