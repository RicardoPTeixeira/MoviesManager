package com.example.ricardo.moviesmanager.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ricardo.moviesmanager.R
import com.example.ricardo.moviesmanager.databinding.ActivityMovieDataBinding
import com.example.ricardo.moviesmanager.models.Filme
import com.example.ricardo.moviesmanager.utils.Constants
import com.example.ricardo.moviesmanager.utils.Modes

class MovieDataActivity : AppCompatActivity() {

    private val amdb: ActivityMovieDataBinding by lazy {
        ActivityMovieDataBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amdb.root)

        amdb.assistidoCb.setOnCheckedChangeListener { compoundButton, b ->
            if(!b) {
                amdb.notaEt.visibility = View.GONE
                amdb.notaTvForm.visibility = View.GONE
            }
            else {
                amdb.notaEt.visibility = View.VISIBLE
                amdb.notaTvForm.visibility = View.VISIBLE
            }
        }

        val mode = intent.getParcelableExtra<Modes>(Constants.MODE)

        if (mode == Modes.VIEW_OR_UPDATE) {
            val movie = intent.getParcelableExtra<Filme>(Constants.MOVIE) ?: return
            amdb.nameEt.setText(movie.name)
            amdb.anoEt.setText(movie.anoLancamento.toString())
            amdb.produtoraEt.setText(movie.produtora)
            amdb.duracaoEt.setText(movie.duracao.toString())
            amdb.assistidoCb.isChecked = movie.assistido
            val nota = movie.nota
            if(nota != null){
                amdb.notaEt.setText(nota.toString())
            }
            val stringArray = resources.getStringArray(R.array.genero)
            stringArray.forEachIndexed { index, s ->
                if (movie.genero == s) amdb.generoSp.setSelection(index)
            }
            amdb.nameEt.isEnabled = false
        }

        amdb.saveBt.setOnClickListener {
            if (mode == Modes.INSERT) finishInsert()
            else finishUpdate()
        }

        amdb.cancelBt.setOnClickListener {
            setResult(RESULT_CANCELED, intent)
            finish()
        }
    }

    private fun finishInsert() {
        if (!validateBeforeFinish()) return
        val name = amdb.nameEt.text.toString()
        val ano = amdb.anoEt.text.toString().toInt()
        val produtora = amdb.produtoraEt.text.toString()
        val duracao = amdb.duracaoEt.text.toString().toInt()
        val assistido = amdb.assistidoCb.isChecked
        val nota = amdb.notaEt.text.toString()
        var notaParaAtribuir: Int? = null
        if(nota.isNotEmpty()) notaParaAtribuir = nota.toInt()
        val genero = amdb.generoSp.selectedItem.toString()
        val movie = Filme(
            id = null,
            name = name,
            anoLancamento = ano,
            produtora = produtora,
            duracao = duracao,
            assistido = assistido,
            nota = notaParaAtribuir,
            genero = genero
        )
        val bundle = Bundle()
        bundle.putParcelable(Constants.MOVIE,movie)
        intent.putExtras(bundle)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun finishUpdate() {
        if (!validateBeforeFinish()) return
        val originalMovie = intent.getParcelableExtra<Filme>(Constants.MOVIE) ?: return
        val name = amdb.nameEt.text.toString()
        val ano = amdb.anoEt.text.toString().toInt()
        val produtora = amdb.produtoraEt.text.toString()
        val duracao = amdb.duracaoEt.text.toString().toInt()
        val assistido = amdb.assistidoCb.isChecked
        val nota = amdb.notaEt.text.toString()
        var notaParaAtribuir: Int? = null
        if(nota.isNotEmpty()) notaParaAtribuir = nota.toInt()
        val genero = amdb.generoSp.selectedItem.toString()
        val movie = Filme(
            id = originalMovie.id,
            name = name,
            anoLancamento = ano,
            produtora = produtora,
            duracao = duracao,
            assistido = assistido,
            nota = notaParaAtribuir,
            genero = genero
        )
        val bundle = Bundle()
        bundle.putParcelable(Constants.MOVIE,movie)
        intent.putExtras(bundle)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun validateBeforeFinish(): Boolean {
        if (validateFields()) return true
        Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_LONG).show()
        return false
    }


    private fun validateFields(): Boolean {
        val name = amdb.nameEt.text.toString()
        val ano = amdb.anoEt.text.toString()
        val produtora = amdb.produtoraEt.text.toString()
        val duracao = amdb.duracaoEt.text.toString()
        val nota = amdb.notaEt.text.toString()
        if (amdb.assistidoCb.isChecked && nota.isEmpty()) return false
        if (name.isEmpty()) return false
        if (ano.isEmpty()) return false
        if (produtora.isEmpty()) return false
        if (duracao.isEmpty()) return false
        return true
    }

}