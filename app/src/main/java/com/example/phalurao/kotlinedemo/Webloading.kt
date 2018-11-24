package com.example.phalurao.kotlinedemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView

class Webloading: AppCompatActivity() {
    var myweb : WebView ? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_layout)
        myweb=findViewById(R.id.web_data)
        myweb!!.loadUrl("https://www.google.co.in/")

    }
}