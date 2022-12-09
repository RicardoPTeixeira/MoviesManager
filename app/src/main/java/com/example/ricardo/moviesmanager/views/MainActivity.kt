package com.example.ricardo.moviesmanager.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ricardo.moviesmanager.adapter.MoviesAdapter
import com.example.ricardo.moviesmanager.databinding.ActivityMainBinding
import com.example.ricardo.moviesmanager.models.Filme

class MainActivity : AppCompatActivity(), OnMovieClickListener {
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var layoutManager: LinearLayoutManager
    val moviesList: ArrayList<Filme> = arrayListOf(Filme(0,"Avengers: ENdgame",2019,"Marvel",true,182,9,"action"))
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        layoutManager = LinearLayoutManager(this)
        amb.movieListRv.layoutManager = layoutManager
        moviesAdapter = MoviesAdapter(moviesList, this)
        amb.movieListRv.adapter = moviesAdapter
    }

    override fun onMovieClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onMovieRemove(position: Int) {
        TODO("Not yet implemented")
    }
}
