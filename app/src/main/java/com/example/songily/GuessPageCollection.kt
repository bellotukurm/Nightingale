package com.example.songily

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_guess_page_collection.*

class GuessPageCollection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_page_collection)

        //***** Shared Preference set Note
        var notes = findViewById<TextView>(R.id.textViewNotes)
        val sharedpreferences = getSharedPreferences("POINTS", Context.MODE_PRIVATE)

        if (sharedpreferences.contains("POINTS")) {
            println("points does")
            notes.text = sharedpreferences.getInt("POINTS", 0).toString()
        } else {
            println("it doesnt")
            notes.text = "0"
        }
        //******

        val intent: Intent = getIntent()

        val lyricText = findViewById<TextView>(R.id.textViewLyric)
        lyricText.text = intent.getStringExtra("LYRIC")

        val songTitle = intent.getStringExtra("TITLE")
        val editSongTitle = findViewById<TextView>(R.id.editTextTitle)

        val songArtist = intent.getStringExtra("ARTIST")
        val editSongArtist = findViewById<TextView>(R.id.editTextArtist)

        guessButton.setOnClickListener {
            val intent = Intent(this, VerdictActivityCollected::class.java)
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

        tellmeButton.setOnClickListener {

            //***** Shared Preference set Note
            val sharedpreferences = getSharedPreferences("POINTS", Context.MODE_PRIVATE)
            val myEditor = sharedpreferences.edit()
            var points = sharedpreferences.getInt("POINTS", 0)

            if (sharedpreferences.contains("POINTS")) {
                println("points does giveup")
                if (points >= 8) {
                    myEditor.putInt("POINTS", (points - 8))
                    myEditor.commit()

                    val intent2 = Intent(this, GiveUpActivity::class.java)
                    intent2.putExtra("LYRIC", intent.getStringExtra("LYRIC"))
                    intent2.putExtra("TITLE", songTitle)
                    intent2.putExtra("ARTIST", songArtist)
                    startActivity(intent2)
                }
            } else {
                //SnackBar
                val parentLayout: View = findViewById<View>(android.R.id.content)
                val snackbar =
                    Snackbar.make(parentLayout, "you don't have enough notes", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }
    }
}
