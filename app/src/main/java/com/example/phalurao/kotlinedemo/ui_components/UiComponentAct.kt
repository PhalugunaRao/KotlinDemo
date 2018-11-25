package com.example.phalurao.kotlinedemo.ui_components

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.example.phalurao.kotlinedemo.R

class UiComponentAct:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_components)
        val buttondialog=findViewById<Button>(R.id.button)
        buttondialog.setOnClickListener{
            val builder=AlertDialog.Builder(this)
            builder.setTitle("Sample Dialog")
            builder.setMessage("My demo dialog message")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            builder.setPositiveButton("Yes"){dialogInterface, which ->
                Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
            }

            builder.setNegativeButton("No"){dialogInterface, which ->
                Toast.makeText(applicationContext,"clicked no",Toast.LENGTH_LONG).show()
            }


            val alertDialog:AlertDialog=builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }
}