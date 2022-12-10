package com.example.ricardo.moviesmanager.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ricardo.moviesmanager.adapter.MoviesAdapter
import com.example.ricardo.moviesmanager.databinding.ActivityMainBinding
import com.example.ricardo.moviesmanager.models.Filme
import com.example.ricardo.moviesmanager.utils.Constants
import com.example.ricardo.moviesmanager.utils.Modes

class MainActivity : AppCompatActivity(), OnMovieClickListener {

    private lateinit var movieARL: ActivityResultLauncher<Intent>
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private val moviesList: ArrayList<Filme> = arrayListOf(Filme(0,"Avengers",2012,"Marvel",true,10,7,"sla"))
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
        movieARL = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if(result.resultCode != RESULT_OK) return@registerForActivityResult

        }
        amb.addBtn.setOnClickListener{
            val intent = Intent(Constants.MOVIE_DATA_ACTIVITY)
            val bundle = Bundle()
            bundle.putParcelable(Constants.MODE, Modes.INSERT)
            intent.putExtras(bundle)
            movieARL.launch(intent)
        }

    }

    override fun onMovieClick(id: Int) {
    }

    override fun onMovieRemove(id: Int) {
    }
}