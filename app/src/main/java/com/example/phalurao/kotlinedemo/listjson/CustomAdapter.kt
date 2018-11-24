package com.example.phalurao.kotlinedemo.listjson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.phalurao.kotlinedemo.R
import com.squareup.picasso.Picasso

class CustomAdapter(context:Context,array_data:ArrayList<ListModel>): BaseAdapter() {
    private val layoutinflat:LayoutInflater
    private val array_data:ArrayList<ListModel>
    val context:Context
    init {
        this.context=context
        this.layoutinflat= LayoutInflater.from(context)
        this.array_data=array_data
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val listrowViewHolder: ListRowViewHolder
        if(convertView==null){
            view=this.layoutinflat.inflate(R.layout.adapter_layout,parent,false)
            listrowViewHolder=ListRowViewHolder(view)
            view.tag=listrowViewHolder
        }else{
            view = convertView
            listrowViewHolder = view.tag as ListRowViewHolder
        }
        listrowViewHolder.tvName.text=array_data.get(position).name
        listrowViewHolder.tvEmail.text=array_data.get(position).email
        listrowViewHolder.tvId.text=array_data.get(position).id
        Picasso.with(context).load(array_data.get(position).icon).placeholder(R.mipmap.ic_launcher).into(listrowViewHolder.imageicon)

   return view
    }



    override fun getItem(position: Int): Any {
        return array_data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return array_data.size
    }
    class ListRowViewHolder(row :View?) {
        public  val tvName : TextView
        public val tvEmail: TextView
        public val tvId: TextView
        public val imageicon:ImageView


        init {
            this.tvName=row?.findViewById(R.id.tvName)as TextView
            this.tvId = row?.findViewById(R.id.tvId) as TextView
            this.tvEmail = row?.findViewById(R.id.tvEmail) as TextView
            this.imageicon=row.findViewById(R.id.image_log)as ImageView

        }

    }
}