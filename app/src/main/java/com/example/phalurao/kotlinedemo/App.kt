package com.example.phalurao.kotlinedemo

import android.app.Application
import com.example.phalurao.kotlinedemo.volleyrequestpack.VolleyService
import io.realm.Realm
import io.realm.RealmConfiguration



class App:Application() {
    override fun onCreate() {
        super.onCreate()
        VolleyService.initialize(this)
        Realm.init(this) // should only be done once when app starts

        val config = RealmConfiguration.Builder()
            .name("myrealm.realm")
            .build()

        Realm.setDefaultConfiguration(config)
    }
}