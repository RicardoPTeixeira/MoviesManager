package com.example.ricardo.moviesmanager.views

import com.example.ricardo.moviesmanager.models.Filme

interface OnMovieClickListener {

    fun onMovieClick(filme: Filme)
    fun onMovieRemove(id: Int)
}