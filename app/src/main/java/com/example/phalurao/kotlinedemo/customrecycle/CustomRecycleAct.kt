package com.example.phalurao.kotlinedemo.customrecycle

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.phalurao.kotlinedemo.R

class CustomRecycleAct:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_recycle_main)
        val myrecycleView=findViewById<RecyclerView>(R.id.recyclerview)
        myrecycleView.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val itemMy=ArrayList<MyData>()
        itemMy.add(MyData("Phalguna",BitmapFactory.decodeResource(resources,R.drawable.ic_launcher)))
        itemMy.add(MyData("Phalguna",BitmapFactory.decodeResource(resources,R.drawable.ic_launcher)))
        itemMy.add(MyData("Phalguna",BitmapFactory.decodeResource(resources,R.drawable.ic_launcher)))
        itemMy.add(MyData("Phalguna",BitmapFactory.decodeResource(resources,R.drawable.ic_launcher)))
        itemMy.add(MyData("Phalguna",BitmapFactory.decodeResource(resources,R.drawable.ic_launcher)))
        itemMy.add(MyData("Phalguna",BitmapFactory.decodeResource(resources,R.drawable.ic_launcher)))
        itemMy.add(MyData("Phalguna",BitmapFactory.decodeResource(resources,R.drawable.ic_launcher)))

        
        val adapter=RecyclerViewAdapter(itemMy)
        myrecycleView.adapter=adapter

    }
}