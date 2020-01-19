package com.example.songily

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycler_adapter.view.*

class RecyclerAdapter(val collection: ArrayList<ArrayList<String>>) : RecyclerView.Adapter<CustomViewHolder>(){

    //val songTitles = listOf<String>("Despasito","Walk it","Shoot me")

    override fun getItemCount(): Int {
        return collection.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.activity_recycler_adapter, parent, false)

        println("booting ")

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        println("happening ")
        if(collection.get(position)!= null){
            val songDetails = collection.get(position)
            val songTitle = songDetails.get(0)
            println("zTitles $songTitle")
            holder.view.titleTextView.text = songTitle
            //onClickListener for recycler view
            holder.itemView.setOnClickListener(object: View.OnClickListener {
                override fun onClick(v: View){
                    val intent = Intent(v.context,GuessPageCollection::class.java )
                    intent.putExtra("LYRIC",songDetails.get(0))
                    intent.putExtra("TITLE",songDetails.get(1))
                    intent.putExtra("ARTIST",songDetails.get(2))
                    v.context.startActivity(intent)
                }
            })
        }
    }

}
class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}
