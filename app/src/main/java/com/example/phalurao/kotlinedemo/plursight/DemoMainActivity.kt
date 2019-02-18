package com.example.phalurao.kotlinedemo.plursight

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import com.example.phalurao.kotlinedemo.R
import kotlinx.android.synthetic.main.demo.*;

class DemoMainActivity:AppCompatActivity(){

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo);

         //setup tool bar
         setSupportActionBar(toolbar)
         supportActionBar!!.title="Showing Browser Result"

        /* //TextView.text  charsequence Object
        var myvalue= tvGreeting.text
         tvGreeting.text="Phalguna"


         //EditText.text Edittable object return
         var str2=etName.text.toString()
         etName.setText("Phaluuuu")
*/



    }

    fun saveName(view:View){
        if(isNotEmpty(etName,inputLayoutName)){
            val personName = etName.text.toString()
            val sp = getSharedPreferences(Constants.APP_SHARED_PREFERENCES,Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString(Constants.KEY_PERSON_NAME,personName)
            editor.apply()
        }
    }

    fun listRepositories(view:View){

        if(isNotEmpty(etRepoName,inputLayoutRepoName)) {
            val queryrepo = etRepoName.text.toString()
            val repoLanguage = etLanguage.text.toString()

            val intent = Intent(this, DisplayActivity::class.java)
            intent.putExtra(Constants.KEY_QUERY_TYPE, Constants.SEARCH_BY_REPO)
            intent.putExtra(Constants.KEY_REPO_SEARCH, queryrepo)
            intent.putExtra(Constants.KEY_LANGUAGE, repoLanguage)
            startActivity(intent)
        }

    }
    fun listUserRepositories(view:View){
        if(isNotEmpty(etGithubUser,inputLayoutGithubUser)){
            val gitUser= etGithubUser.text.toString()

            val intent= Intent(this,DisplayActivity::class.java)
            intent.putExtra(Constants.KEY_QUERY_TYPE,Constants.SEARCH_BY_USER)
            intent.putExtra(Constants.KEY_GITHUB_USER,gitUser)
            startActivity(intent)
        }


    }

    fun isNotEmpty(editText: EditText,textInputLayout: TextInputLayout ):Boolean{
        if(editText.text.toString().isEmpty()){
            textInputLayout.error="Can not be blank"
            return false
        }else{
            textInputLayout.isErrorEnabled=false
            return  true
        }

    }

}
