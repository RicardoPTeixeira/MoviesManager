package com.example.ricardo.moviesmanager.controller

import com.example.ricardo.moviesmanager.models.Filme

class OrdenadorFilme {

    fun ordenarPorNome(list : MutableList<Filme>): MutableList<Filme> {
        list.sortBy { it.name }
        return list
    }

    fun ordenarPorNota(list : MutableList<Filme>): MutableList<Filme> {
        list.sortBy { it.nota }
        list.reverse()
        return list
    }
}