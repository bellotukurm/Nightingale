package com.example.songily

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_guess_page1.*

class GuessPage1() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_page1)

        val intent: Intent = getIntent()

        val lyricText = findViewById<TextView>(R.id.textViewLyric)
        lyricText.text = intent.getStringExtra("LYRIC")

        val songTitle = intent.getStringExtra("TITLE")
        val editSongTitle = findViewById<TextView>(R.id.editTextTitle)

        val songArtist = intent.getStringExtra("ARTIST")
        val editSongArtist = findViewById<TextView>(R.id.editTextArtist)

        guessButton.setOnClickListener {
            val intent = Intent(this, VerdictActivity::class.java)
            if (editSongTitle.text.contains(songTitle, ignoreCase = true)) {
                println("true")
                println(editSongTitle.text)
                println(songTitle)
                intent.putExtra("VERDICT_TITLE", true)
            } else {
                println("false")
                println(editSongTitle.text)
                println(songTitle)
                intent.putExtra("VERDICT_TITLE", false)
            }

            if (editSongArtist.text.contains(songArtist, ignoreCase = true)) {
                println("true")
                println(editSongArtist.text)
                println(songArtist)
                intent.putExtra("VERDICT_ARTIST", true)
            } else {
                println("false")
                println(editSongArtist.text)
                println(songArtist)
                intent.putExtra("VERDICT_ARTIST", false)
            }
            startActivity(intent)
        }
    }

}
