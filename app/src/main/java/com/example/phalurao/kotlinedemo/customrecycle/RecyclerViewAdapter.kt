package com.example.phalurao.kotlinedemo.customrecycle

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.phalurao.kotlinedemo.R

class RecyclerViewAdapter(val list:ArrayList<MyData>):RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.volley_adapter_row,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, p1: Int) {
        holder.bindData(list[p1])
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        fun bindData(data:MyData) {
            val _textView: TextView = itemView.findViewById(R.id.newsTitle)
            val _imageView: ImageView = itemView.findViewById(R.id.image)
            _textView.text = data.text
            _imageView.setImageBitmap(data.image)
        }

    }

}