package com.example.intentcomretorno

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.intentcomretorno.databinding.ActivityPrincipalBinding

class Principal : AppCompatActivity() {

    private val perguntasUsadas = mutableListOf<Int>()
    private lateinit var binding: ActivityPrincipalBinding

    fun verificarResposta(Clicado: Int, opcaooCorreta: Int): Boolean{
        return Clicado == opcaooCorreta
        }

    fun proximaPerguntaNoRepeticao(): Int? {
        val numQuestao = resources.getStringArray(R.array.perguntas_quiz).size / 5
        //pega o numero total de Strings que ta dentro do array e divide por 5
        //sendo questao/alt1/alt2/alt3/opçãoCorreta

        val disponiveis = (0 until numQuestao).filter { it !in perguntasUsadas}
        //seta quais sao as alternativas disponiveis, ele pega todas de 0 até numQuestoes
        //sendo "numQuestoes" o total de quetoes e filtra ela para ter todas as questoes
        //menos as que ja foram usadas, que ja estao dentro de perguntasUsadas

        if(disponiveis.isEmpty()){
            perguntasUsadas.clear()
            return (0 until numQuestao).random()
        } //confere se todas as perguntas que tem no banco ja foram usadas
        //se todas tiverem sido usadas, ele limpa o banco de perguntasUsadas e gera
        //uma nova pergunta

        val escolhida = disponiveis.random()
        perguntasUsadas.add(escolhida)
        return escolhida
        //depois de passar por todas as conferencias ele gera uma Escolhida e ja
        //marca ela como usada e retorna ela na função, com o return escolhida
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val nomeRecebido = intent.getStringExtra("nome")
        binding.playerText.text = "Player: " + nomeRecebido

        var pontos: Int = 0
        var errou: Int = 0
        var newQuest = (proximaPerguntaNoRepeticao() ?: 0) * 5
        val array = resources.getStringArray(R.array.perguntas_quiz)
        var correta = array[newQuest + 4].toInt()  // 1 (B)


        binding.perguntaText.text = array[newQuest]
        binding.buttonA.text = array[newQuest + 1]
        binding.buttonB.text = array[newQuest + 2]
        binding.buttonC.text = array[newQuest + 3]


        binding.buttonA.setOnClickListener {
            if(verificarResposta(0, correta)) {
                pontos++
                binding.pontosText.text = "Pontos: " + pontos + " / 15"

            }
            else{
                errou++
                binding.erradasText.text = "Errou: " + errou
                if(errou == 2){
                    val a = Intent(this, FinalActivity::class.java)
                    a.putExtra("pontuação", pontos)
                    startActivity(a)
                    overridePendingTransition(0, 0)
                    finish()
                }
            }

            if(pontos == 15){
                val b = Intent(this, FinalActivity::class.java)
                b.putExtra("pontuação", pontos)
                startActivity(b)
                overridePendingTransition(0, 0)
                finish()
            }

            newQuest = (proximaPerguntaNoRepeticao() ?: 0) * 5
            correta = array[newQuest + 4].toInt() //mudar a correta
            //atualizar a UI
            binding.perguntaText.text = array[newQuest]
            binding.buttonA.text = array[newQuest + 1]
            binding.buttonB.text = array[newQuest + 2]
            binding.buttonC.text = array[newQuest + 3]
        }

        binding.buttonB.setOnClickListener {
            if(verificarResposta(1, correta)) {
                pontos++
                binding.pontosText.text = "Pontos: " + pontos + " / 15"
            }

            else{
                errou++
                binding.erradasText.text = "Errou: " + errou
                if(errou == 2){
                    val a = Intent(this, FinalActivity::class.java)
                    a.putExtra("pontuação", pontos)
                    startActivity(a)
                    overridePendingTransition(0, 0)
                    finish()
                }
            }

            if(pontos == 15){
                val b = Intent(this, FinalActivity::class.java)
                b.putExtra("pontuação", pontos)
                startActivity(b)
                overridePendingTransition(0, 0)
                finish()
            }

            newQuest = (proximaPerguntaNoRepeticao() ?: 0) * 5
            correta = array[newQuest + 4].toInt() //mudar a correta
            //atualizar a UI
            binding.perguntaText.text = array[newQuest]
            binding.buttonA.text = array[newQuest + 1]
            binding.buttonB.text = array[newQuest + 2]
            binding.buttonC.text = array[newQuest + 3]
        }

        binding.buttonC.setOnClickListener {
            if(verificarResposta(2, correta)) {
                pontos++
                binding.pontosText.text = "Pontos: " + pontos + " / 15"
            }

            else{
                errou++
                binding.erradasText.text = "Errou: " + errou
                if(errou == 2){
                    val a = Intent(this, FinalActivity::class.java)
                    a.putExtra("pontuação", pontos)
                    startActivity(a)
                    overridePendingTransition(0, 0)
                    finish()
                }
            }

            if(pontos == 15){
                val b = Intent(this, FinalActivity::class.java)
                b.putExtra("pontuação", pontos)
                startActivity(b)
                overridePendingTransition(0, 0)
                finish()
            }

            newQuest = (proximaPerguntaNoRepeticao() ?: 0) * 5
            correta = array[newQuest + 4].toInt() //mudar a correta
            //atualizar a UI
            binding.perguntaText.text = array[newQuest]
            binding.buttonA.text = array[newQuest + 1]
            binding.buttonB.text = array[newQuest + 2]
            binding.buttonC.text = array[newQuest + 3]
        }

    }
}