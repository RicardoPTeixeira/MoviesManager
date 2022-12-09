package com.example.ricardo.moviesmanager.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ricardo.moviesmanager.R
import com.example.ricardo.moviesmanager.models.Filme
import com.example.ricardo.moviesmanager.views.OnMovieClickListener

class MoviesAdapter(
    private val movieList: MutableList<Filme>,
    private val onMemberClickListener: OnMovieClickListener,
): RecyclerView.Adapter<MoviesAdapter.MemberViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MemberViewHolder {
        val layoutMemberView: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_item, parent, false)
        return MemberViewHolder(layoutMemberView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MoviesAdapter.MemberViewHolder, position: Int) {
        val movie = movieList[position]
        holder.nameTv.text = movie.nome
        holder.anoTv.text = "Lançamento: "+movie.anoLancamento
        holder.produtoraTv.text = "Produtora: "+movie.produtora
        holder.duracaoTv.text = "Duração: "+movie.duracao
        val assistido = movie.assistido
        if(assistido) holder.assistidoTv.text = "Assistido"
        else holder.assistidoTv.text = "Não assistido"
        holder.notaTv.text =  "Nota: "+movie.nota
        holder.generoTv.text =  "Gênero: "+movie.genero
        holder.removeImg.setOnClickListener{
            println("Clicando so na imagem")
        }
        holder.itemView.setOnClickListener{
            onMemberClickListener.onMovieClick(position)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MemberViewHolder(layoutMemberView: View): RecyclerView.ViewHolder(layoutMemberView){

        val nameTv: TextView = layoutMemberView.findViewById(R.id.nameTv)
        val anoTv: TextView = layoutMemberView.findViewById(R.id.anoTv)
        val notaTv: TextView = layoutMemberView.findViewById(R.id.notaTv)
        val produtoraTv: TextView = layoutMemberView.findViewById(R.id.produtoraTv)
        val duracaoTv: TextView = layoutMemberView.findViewById(R.id.duracaoTv)
        val assistidoTv: TextView = layoutMemberView.findViewById(R.id.assistidoTv)
        val generoTv: TextView = layoutMemberView.findViewById(R.id.generoTv)
        val removeImg: ImageView = layoutMemberView.findViewById(R.id.removeImg)

    }
}