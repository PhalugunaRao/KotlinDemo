package com.example.phalurao.kotlinedemo

import android.app.Application
import com.example.phalurao.kotlinedemo.volleyrequestpack.VolleyService

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        VolleyService.initialize(this)
    }
}