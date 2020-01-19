package com.example.songily

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class VerdictActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verdict)


        val returnButton = findViewById<Button>(R.id.buttonReturn)
        returnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val intent: Intent = getIntent()

        val v1 = findViewById<TextView>(R.id.textViewVerdict1)
        val v2 = findViewById<TextView>(R.id.textViewVerdict2)
        val verdict1 = intent.getBooleanExtra("VERDICT_TITLE",true)
        val verdict2 = intent.getBooleanExtra("VERDICT_ARTIST",true)

        val sharedPref: SharedPreferences = getSharedPreferences("POINTS", Context.MODE_PRIVATE)
        val myEditor = sharedPref.edit()
        var points = sharedPref.getInt("POINTS",0)

        if(verdict1){
            v1.text = "Correct"
            v1.setBackgroundColor(Color.GREEN)
            myEditor.putInt("POINTS",(points + 4))
            myEditor.commit()
        }else{
            v1.text = "Fail"
            v1.setBackgroundColor(Color.RED)
        }

        points = sharedPref.getInt("POINTS",0)
        println(points)
        if(verdict2){
            v2.text = "Correct"
            v2.setBackgroundColor(Color.GREEN)
            myEditor.putInt("POINTS",(points + 2))
            myEditor.commit()
        }else{
            v2.text = "Fail"
            v2.setBackgroundColor(Color.RED)
        }

        //***** Shared Preference set Note
        var notes = findViewById<TextView>(R.id.textViewNotes)
        val sharedpreferences = getSharedPreferences("POINTS", Context.MODE_PRIVATE)

        if(sharedpreferences.contains("POINTS")){
            println("points does")
            notes.text=  sharedpreferences.getInt("POINTS",0).toString()
        }else{
            println("it doesnt")
            notes.text=  "0"
        }
        //*****

    }
}
