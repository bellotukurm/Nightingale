package com.example.songily

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_guess_collection_adder.*
import android.util.Log
import android.view.View
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class GuessCollectionAdder : AppCompatActivity() {

    var mLists: ArrayList<ArrayList<String>> = ArrayList()
    var jsonlistOfListsAfter: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_collection_adder)


        //****

        val detailsList: MutableList<String> = ArrayList()
        val intent: Intent = getIntent()

        val songLyric = intent.getStringExtra("LYRIC")
        val songTitle = intent.getStringExtra("TITLE")
        val songArtist = intent.getStringExtra("ARTIST")

        detailsList.add(songLyric)
        detailsList.add(songTitle)
        detailsList.add(songArtist)
        println("added to list")

        val gson = Gson()
        val toJson = gson.toJson(detailsList)

        //***** Shared Preference save song to list
        val sharedpreferences = getSharedPreferences("LIST", Context.MODE_PRIVATE)
        val myEditor = sharedpreferences.edit()
        var jsonlistOfLists = sharedpreferences.getString("LIST", "")
        if (sharedpreferences.contains("LIST")) {
            println("LISTS does")
            myEditor.putString("LIST", jsonlistOfLists.plus("\n$toJson"))
            myEditor.commit()
        } else {
            println("LISTS doesnt")
            myEditor.putString("LIST", "$toJson")
            myEditor.commit()
        }

        jsonlistOfListsAfter = sharedpreferences.getString("LIST", "").toString()

        //***
        println("jsonlistOfListsAfter")

        //Adapter

        val listOfLists = jsonlistOfListsAfter?.split("\n")?.toTypedArray()
        if (listOfLists != null) {
            for (item in listOfLists) {
                print("an item")
                println(item)
            }
        }

        println(mLists)
        if (listOfLists != null) {
            for (i in 0..listOfLists.size - 1) {
                println("the gson")
                val type = object : TypeToken<ArrayList<String>>() {}.type
                println(gson.fromJson<ArrayList<String>>(listOfLists[i], type))
                mLists.add(gson.fromJson<ArrayList<String>>(listOfLists[i], type))
            }
        }

        //SnackBar
        val parentLayout: View = findViewById<View>(android.R.id.content)
        val snackbar = Snackbar.make(parentLayout, "Lyric Added", Snackbar.LENGTH_LONG)
        snackbar.show()

        Log.d("Snacky", "worked")

        println(mLists)
        //*****

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(mLists)

        val homeButton = findViewById<Button>(R.id.homeButton2)
        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
