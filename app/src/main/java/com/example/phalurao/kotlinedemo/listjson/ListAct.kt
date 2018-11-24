package com.example.phalurao.kotlinedemo.listjson

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import com.example.phalurao.kotlinedemo.R
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class ListAct: AppCompatActivity() {
    lateinit var progresss:ProgressBar
    lateinit var lista_view:ListView
    var array_data:ArrayList<ListModel> = ArrayList()
    val client=OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_main)
        progresss=findViewById(R.id.progress_view)
        progresss.visibility=View.VISIBLE
        lista_view=findViewById(R.id.listView_data) as ListView
        run("http://www.json-generator.com/api/json/get/bZtxTJVgoi?indent=2")

    }

    private fun run(url: String) {
        progresss.visibility=View.VISIBLE
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object :Callback{
            override fun onResponse(call: Call, response: Response) {
                var str_response=response.body()!!.string()
                val json_contact: JSONObject = JSONObject(str_response)
                var jsonarray_info: JSONArray = json_contact.getJSONArray("info")
                var i:Int = 0
                var size:Int = jsonarray_info.length()
                array_data= ArrayList();
                for (i in 0.. size-1) {
                    var json_objectdetail:JSONObject=jsonarray_info.getJSONObject(i)
                    var model:ListModel= ListModel();
                    model.id=json_objectdetail.getString("id")
                    model.name=json_objectdetail.getString("name")
                    model.email=json_objectdetail.getString("email")
                    model.icon=json_objectdetail.getString("icon")
                    array_data.add(model)
                }
                runOnUiThread {
                    //stuff that updates ui
                    val obj_adapter : CustomAdapter
                    obj_adapter = CustomAdapter(applicationContext,array_data)
                    lista_view.adapter=obj_adapter
                    progresss.visibility = View.GONE
                }
               // progresss.visibility = View.GONE
            }

            override fun onFailure(call: Call, e: IOException) {
                progresss.visibility=View.GONE
            }
        })

    }
}