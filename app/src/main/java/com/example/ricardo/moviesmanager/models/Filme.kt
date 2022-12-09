package com.example.ricardo.moviesmanager.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize class Filme(
    val id : Int,
    val nome: String,
    val anoLancamento: Int,
    val produtora: String,
    val assistido : Boolean,
    val duracao: Int,
    val nota: Int,
    val genero : String
) : Parcelable