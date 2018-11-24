package com.example.phalurao.kotlinedemo.volleyrequestpack

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.NetworkImageView
import com.example.phalurao.kotlinedemo.R
import org.json.JSONArray
import org.json.JSONObject

class VolleyNewAct :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.volley_list_act)

        val newsRecyclerView = findViewById(R.id.news_list) as RecyclerView
        newsRecyclerView.layoutManager = LinearLayoutManager(this)

        val url = "http://query.yahooapis.com/v1/public/yql?q=" +
                "select * from rss where url='http://rss.news.yahoo.com/rss/topstories'" +
                "&format=json"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener<JSONObject> { response ->
                val news = response
                    .getJSONObject("query")
                    .getJSONObject("results")
                    .getJSONArray("item")

                newsRecyclerView.adapter = NewsAdapter(news)
            },
            Response.ErrorListener {
                Toast.makeText(this, "That didn't work!", Toast.LENGTH_SHORT).show()
            })

        VolleyService.requestQueue.add(request)
        VolleyService.requestQueue.start()
    }

    class NewsAdapter(val news: JSONArray) : RecyclerView.Adapter<NewsViewHolder>() {
        @SuppressLint("NewApi")
        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            holder.bind(news.getJSONObject(position), position)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.volley_adapter_row, parent, false)
            return NewsViewHolder(view)
        }

        override fun getItemCount(): Int = news.length()
    }

    class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(newsItem: JSONObject, position: Int) {
            val title = view.findViewById(R.id.newsTitle) as TextView
            val image = view.findViewById(R.id.image) as NetworkImageView
            title.text = Html.fromHtml(newsItem["title"].toString(), 0)
            image.setImageUrl(
                "http://loremflickr.com/800/600/cat?random=$position",
                VolleyService.imageLoader)
        }
    }

}