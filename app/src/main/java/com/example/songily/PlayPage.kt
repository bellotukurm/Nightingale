package com.example.songily

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_play_page.*


class PlayPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_page)

        //***** Shared Preference set Note
        var notes1 = findViewById<TextView>(R.id.textViewNotes1)
        var notes2 = findViewById<TextView>(R.id.textViewNotes2)
        val sharedpreferences = getSharedPreferences("POINTS", Context.MODE_PRIVATE)

        if(sharedpreferences.contains("POINTS")){
            println("points does")
            notes1.text=  sharedpreferences.getInt("POINTS",0).toString()
            notes2.text=  sharedpreferences.getInt("POINTS",0).toString()
        }else{
            println("it doesnt")
            notes1.text=  "0"
            notes2.text=  "0"
        }
        //*****


        val findButtonCurrent = findViewById<Button>(R.id.findButtonCurrent)
        findButtonCurrent.setOnClickListener{
            val intent = Intent(this, ActivityMap1::class.java)
            startActivity(intent)
        }

        guessCollectionButtonCurrent.setOnClickListener{
            val intent = Intent(this, GuessCollectionArchive::class.java)
            startActivity(intent)
        }

        findButtonClassic.setOnClickListener{
            val intent = Intent(this, ActivityMap2::class.java)
            startActivity(intent)
        }

        guessCollectionButtonClassic.setOnClickListener{
            val intent = Intent(this, GuessCollectionArchive::class.java)
            startActivity(intent)
        }

        floatingHomeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
