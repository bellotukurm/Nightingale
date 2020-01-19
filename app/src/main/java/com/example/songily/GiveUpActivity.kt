package com.example.songily

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_give_up.*

class GiveUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_give_up)

        val intent: Intent = getIntent()

        val lyricText = findViewById<TextView>(R.id.textViewLyric)
        lyricText.text = intent.getStringExtra("LYRIC")

        val titleText = findViewById<TextView>(R.id.textViewTitle)
        titleText.text = intent.getStringExtra("TITLE")

        val artistText = findViewById<TextView>(R.id.textViewArtist)
        artistText.text = intent.getStringExtra("ARTIST")

        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
