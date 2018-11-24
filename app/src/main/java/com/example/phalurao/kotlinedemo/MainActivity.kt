package com.example.phalurao.kotlinedemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.phalurao.kotlinedemo.listjson.ListAct
import com.example.phalurao.kotlinedemo.volleyrequestpack.VolleyNewAct
import kotlinx.android.synthetic.main.web_layout.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webtext=findViewById(R.id.load_webview)as TextView
        val listText=findViewById(R.id.list_view)as TextView
        webtext.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(applicationContext, VolleyNewAct::class.java)
                startActivity(intent)
            }
        })
        listText.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(applicationContext,ListAct::class.java)
                startActivity(intent)
            }
        })
    }


}
