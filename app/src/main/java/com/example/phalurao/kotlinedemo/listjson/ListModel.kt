package com.example.phalurao.kotlinedemo.listjson

import android.provider.ContactsContract

class ListModel {
    lateinit var id:String
    lateinit var icon:String
    lateinit var name:String
    lateinit var email:String

    constructor(id:String,icon:String,name:String,email:String){
        this.id=id
        this.icon=icon
        this.name=name
        this.email=email
    }
    constructor()
}