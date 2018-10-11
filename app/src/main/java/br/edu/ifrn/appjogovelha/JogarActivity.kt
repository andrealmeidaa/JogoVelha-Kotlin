package br.edu.ifrn.appjogovelha

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import br.edu.ifrn.appjogovelha.R.id.btn1
import br.edu.ifrn.appjogovelha.R.layout.activity_jogar
import kotlinx.android.synthetic.main.activity_jogar.*
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class JogarActivity : AppCompatActivity() {

    //    Resultados Vencedores Possíveis
    //
    //    +---+---+---+
    //    | 1 | 2 | 3 |
    //    +---+---+---+
    //    | 4 + 5 | 6 |
    //    +---+---+---+
    //    | 7 | 8 | 9 |
    //    +---+---+---+
    //
    val r = ArrayList<Int>()
    var g = ArrayList<Int>()

    var j1:String = ""
    var j2:String = ""
    var placar1: Int = 0
    var placar2: Int = 0
    var jogadorAtual: Int = 1
    var t = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_jogar)

        val argumentos: Bundle = intent.extras

        j1 = argumentos.getString("jogador1")
        j2 = argumentos.getString("jogador2")
        placar1 = argumentos.getString("placar1").toInt()
        placar2 = argumentos.getString("placar2").toInt()

        tv_jogador1.text = j1.toString()
        tv_jogador2.text = j2.toString()

        atualizaPlacar()
        g.clear()
        for (i in 1..10) { g.add(0) }

    }

    /**
     *  Converte o valor da propriedade Tag para Int e passa os parâmentros
     *  para a função Jogar()
     */
    fun btnPosicao(view: View) = jogar(view.tag.toString().toInt(), view as Button)

    /**
     *   Parâmentros:  @ valor da propriedade Tag do botão
     *                 @ botão pressionado
     */
    fun jogar(jogada: Int, btnAtual: Button) {

        btnAtual.isClickable = false

        if (jogadorAtual == 1) {
            btnAtual.text = "X"
            btnAtual.setBackgroundResource(R.color.colorJogador1)
            g.set(jogada, 1)
            this.jogadorAtual = 2
        } else {
            btnAtual.text = "O"
            btnAtual.setBackgroundResource(R.color.colorJogador2)
            g.set(jogada, -1)
            this.jogadorAtual = 1
        }

        checkGrid()
    }

    /**
     *   Check o jogador vencedor
     */

    fun checkGrid() {

        r.clear()

        r.add(g[1]+g[2]+g[3])
        r.add(g[4]+g[5]+g[6])
        r.add(g[7]+g[8]+g[9])
        r.add(g[1]+g[4]+g[7])
        r.add(g[2]+g[5]+g[8])
        r.add(g[3]+g[6]+g[9])
        r.add(g[1]+g[5]+g[9])
        r.add(g[3]+g[5]+g[7])

        for (i in r) {

            if (i == 3) {
                this.placar1++
                atualizaPlacar()
                btnTrava()
                Toast.makeText(this, "${this.j1.toString()} (X) Venceu", Toast.LENGTH_LONG).show()
                // Thread.sleep(2_000)
                // limpar(btn1)
                return
            }
            if (i == -3) {
                this.placar2++
                atualizaPlacar()
                btnTrava()
                Toast.makeText(this, "${this.j2.toString()} (O) Venceu", Toast.LENGTH_LONG).show()
                // Thread.sleep(2_000)
                // limpar(btn1)
                return
            }
        }

        t=0
        for (i in g) {
            if (i==1 || i==-1) { t++ }
        }

        if (t==9) {
            btnTrava()
            Toast.makeText(this, "Não houve vencedor", Toast.LENGTH_LONG).show()
        }

    }

    fun limpar(limpar_view: View) {
        g.clear()
        for (i in 0..10) { g.add(i,0) }

        btn1.isClickable = true
        btn2.isClickable = true
        btn3.isClickable = true
        btn4.isClickable = true
        btn5.isClickable = true
        btn6.isClickable = true
        btn7.isClickable = true
        btn8.isClickable = true
        btn9.isClickable = true

        btn1.text=""
        btn2.text=""
        btn3.text=""
        btn4.text=""
        btn5.text=""
        btn6.text=""
        btn7.text=""
        btn8.text=""
        btn9.text=""

        btn1.setBackgroundResource(android.R.drawable.btn_default);
        btn2.setBackgroundResource(android.R.drawable.btn_default);
        btn3.setBackgroundResource(android.R.drawable.btn_default);
        btn4.setBackgroundResource(android.R.drawable.btn_default);
        btn5.setBackgroundResource(android.R.drawable.btn_default);
        btn6.setBackgroundResource(android.R.drawable.btn_default);
        btn7.setBackgroundResource(android.R.drawable.btn_default);
        btn8.setBackgroundResource(android.R.drawable.btn_default);
        btn9.setBackgroundResource(android.R.drawable.btn_default);

    }

    fun restart(vencedor_view: View) {

        val i  = Intent(this, JogarActivity::class.java)
        val param: Bundle = Bundle()
        param.putString("jogador1", j1.toString())
        param.putString("jogador2", j2.toString())
        param.putString("placar1", placar1.toString())
        param.putString("placar2", placar2.toString())
        i.putExtras(param)
        finish()
        startActivity(i)

    }

    fun atualizaPlacar() {
        tv_placar.text = "${placar1}"+"/"+"${placar2}"
    }

    fun fim(view: View) {
        val mainIntent  = Intent(this, MainActivity::class.java)
        finish()
        startActivity(mainIntent)
    }

    fun btnTrava() {
        btn1.isClickable = false
        btn2.isClickable = false
        btn3.isClickable = false
        btn4.isClickable = false
        btn5.isClickable = false
        btn6.isClickable = false
        btn7.isClickable = false
        btn8.isClickable = false
        btn9.isClickable = false
    }
}
