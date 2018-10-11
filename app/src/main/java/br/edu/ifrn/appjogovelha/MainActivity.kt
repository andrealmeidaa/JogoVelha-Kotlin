package br.edu.ifrn.appjogovelha

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import br.edu.ifrn.appjogovelha.R.layout.activity_jogar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun jogar(v: View?) {

        val jogador1: String = etx_jogador1.text.toString()
        val jogador2: String = etx_jogador2.text.toString()

        if (jogador1.isEmpty() || jogador2.isEmpty()) {

            Toast.makeText(this, "Informe o nome dos Jogadores", Toast.LENGTH_LONG).show()

        } else {

            val i  = Intent(this, JogarActivity::class.java)
            val param: Bundle = Bundle()
            param.putString("jogador1", jogador1)
            param.putString("jogador2", jogador2)
            param.putString("placar1", "0")
            param.putString("placar2", "0")
            i.putExtras(param)
            startActivity(i)

        }
    }
}
