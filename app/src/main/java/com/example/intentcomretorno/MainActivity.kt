package com.example.intentcomretorno

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.intentcomretorno.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicia música Champions League
        tocarMusicaChampions()

        binding.alterarNome.setOnClickListener {
            val nomeEscolhido = binding.editText.text.toString().trim()
            val i = Intent(this, Principal::class.java)

            if (nomeEscolhido.isNotEmpty()) {
                i.putExtra("nome", nomeEscolhido)
                startActivity(i)
                overridePendingTransition(0, 0)
                finish()
            } else {
                Toast.makeText(this, "Por favor, digite um nome!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun tocarMusicaChampions() {
        mediaPlayer = MediaPlayer.create(this, R.raw.champions_league)  // MP3 em res/raw/
        mediaPlayer?.isLooping = true  // Loop infinito
        mediaPlayer?.setVolume(0.5f, 0.5f)  // Volume 50%
        mediaPlayer?.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()  // Pausa ao sair
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer?.start()  // Retoma ao voltar
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()  // Libera memória
        mediaPlayer = null
    }
}
