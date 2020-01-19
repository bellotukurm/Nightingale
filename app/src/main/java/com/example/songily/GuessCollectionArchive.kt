package com.example.songily

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_guess_collection_adder.*

class GuessCollectionArchive : AppCompatActivity() {

    var mLists: ArrayList<ArrayList<String>> = ArrayList()
    var jsonlistOfListsAfter: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_collection_archive)

        val sharedpreferences = getSharedPreferences("LIST", Context.MODE_PRIVATE)
        jsonlistOfListsAfter = sharedpreferences.getString("LIST", "").toString()

        println("jsonlistOfListsAfter")

        val listOfLists = jsonlistOfListsAfter?.split("\n")?.toTypedArray()
        if (listOfLists != null) {
            for (item in listOfLists) {
                print("an item")
                println(item)
            }
        }

        val gson = Gson()
        println(mLists)
        if (listOfLists != null) {
            for (i in 0..listOfLists.size - 1) {
                println("the gson")
                val type = object : TypeToken<ArrayList<String>>() {}.type
                println(gson.fromJson<ArrayList<String>>(listOfLists[i], type))
                mLists.add(gson.fromJson<ArrayList<String>>(listOfLists[i], type))
            }
        }

        val homeButton = findViewById<Button>(R.id.homeButton1)
        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(mLists)


    }
}
