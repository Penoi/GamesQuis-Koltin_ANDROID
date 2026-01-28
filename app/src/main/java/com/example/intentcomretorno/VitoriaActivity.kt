package com.example.intentcomretorno

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.intentcomretorno.databinding.ActivityVitoriaBinding

class VitoriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVitoriaBinding

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVitoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tocarMusicaChampions()

        binding.button.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()
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