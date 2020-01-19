package com.example.songily

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var notes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        //*****
        val parentLayout: View = findViewById<View>(android.R.id.content)
        val snacky = Snackbar.make(parentLayout,"sup",Snackbar.LENGTH_LONG)
        snacky.show()

        val playButton = findViewById<Button>(R.id.playButton)
        playButton.setOnClickListener {
            val intent = Intent(this, PlayPage::class.java)
            startActivity(intent)
        }


        //***** Shared Preference check if enough note to enter unlock all songs
        val myEditor = sharedpreferences.edit()
        var points = sharedpreferences.getInt("POINTS", 0)


        //Setters
        //myEditor.putInt("POINTS",10)
        //myEditor.commit()
        //myEditor.putBoolean("ALLSONGS",false)
        //myEditor.commit()

        //***** Shared Preference set Note

        val allSongsButton = findViewById<Button>(R.id.allSongsButton)
        allSongsButton.setOnClickListener {

            if (sharedpreferences.contains("ALLSONGS")) {
                println("allsongs does")
            } else {
                myEditor.putBoolean("ALLSONGS", false)
                myEditor.commit()
            }

            if (sharedpreferences.contains("POINTS") && sharedpreferences.contains("ALLSONGS")) {
                println("points does giveup")
                if ((points >= 500) || (sharedpreferences.getBoolean("ALLSONGS", true))) {
                    if (points >= 500) {
                        allSongsButton.text = "VIEW ALL SONGS"
                        println("deleted")
                        myEditor.putInt("POINTS", (points - 500))
                        myEditor.commit()
                    }
                    myEditor.putBoolean("ALLSONGS", true)
                    val intent = Intent(this, AllSongsActivity::class.java)
                    startActivity(intent)
                } else {
                    val parentLayout: View = findViewById<View>(android.R.id.content)
                    val snackbar =
                        Snackbar.make(parentLayout, "you need 500 notes", Snackbar.LENGTH_LONG)
                    snackbar.show()
                }
            } else {
                //SnackBar
                val parentLayout: View = findViewById<View>(android.R.id.content)
                val snackbar =
                    Snackbar.make(parentLayout, "you need 500 notes", Snackbar.LENGTH_LONG)
                snackbar.show()
            }

        }

        val learnButton = findViewById<Button>(R.id.learnButton)
        learnButton.setOnClickListener {
            val intent = Intent(this, LearnPage::class.java)
            startActivity(intent)
        }
    }
}
