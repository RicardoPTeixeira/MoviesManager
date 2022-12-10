package com.example.ricardo.moviesmanager.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movie")
class Filme(
    @PrimaryKey(autoGenerate = true)
    val id : Int?,
    @NonNull
    val name: String,
    @NonNull
    val anoLancamento: Int,
    @NonNull
    val produtora: String,
    @NonNull
    val assistido : Boolean,
    @NonNull
    val duracao: Int,
    val nota: Int?,
    @NonNull
    val genero : String
) : Parcelable