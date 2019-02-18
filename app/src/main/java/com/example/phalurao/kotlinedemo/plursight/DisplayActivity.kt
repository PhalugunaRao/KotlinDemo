package com.example.phalurao.kotlinedemo.plursight

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem

import com.example.phalurao.kotlinedemo.R


import java.util.HashMap

import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_display.*
import kotlinx.android.synthetic.main.header.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DisplayActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mDisplayAdapter: DisplayAdapter? = null
    private var browsedRepositories: List<Repository>? = null
    private var mService: GithubAPIService? = null
    private var mRealm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Showing Browsed Results"

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView!!.layoutManager = layoutManager

        mService = RetrofitClient.getGithubAPIService()
        mRealm = Realm.getDefaultInstance()

        navigationView.setNavigationItemSelectedListener(this)

        val drawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawerLayout!!.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        val intent = intent
        if (intent.getIntExtra(Constants.KEY_QUERY_TYPE, -1) == Constants.SEARCH_BY_REPO) {
            val queryRepo = intent.getStringExtra(Constants.KEY_REPO_SEARCH)
            val repoLanguage = intent.getStringExtra(Constants.KEY_LANGUAGE)
            fetchRepositories(queryRepo, repoLanguage)
        } else {
            val githubUser = intent.getStringExtra(Constants.KEY_GITHUB_USER)
            fetchUserRepositories(githubUser)
        }
    }

    private fun setAppUserName(){
        val sp=getSharedPreferences(Constants.APP_SHARED_PREFERENCES,Context.MODE_PRIVATE)
        val personName = sp.getString(Constants.KEY_PERSON_NAME,"User")
        val headerView = navigationView.getHeaderView(0)
        headerView.txvName.text = personName

    }

    private fun fetchUserRepositories(githubUser: String) {

        mService!!.searchRepositoriesByUser(githubUser).enqueue(object : Callback<List<Repository>> {
            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                if (response.isSuccessful) {
                    Log.i(TAG, "posts loaded from API $response")

                    browsedRepositories = response.body()

                    if (browsedRepositories != null && browsedRepositories!!.size > 0)
                        setupRecyclerView(browsedRepositories)
                    else
                        Util.showMessage(this@DisplayActivity, "No Items Found")

                } else {
                    Log.i(TAG, "Error $response")
                    Util.showErrorMessage(this@DisplayActivity, response.errorBody()!!)
                }
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                Util.showMessage(this@DisplayActivity, t.message)
            }
        })
    }

    private fun fetchRepositories(queryRepo: String, repoLanguage: String?) {
        var queryRepo = queryRepo

        val query = HashMap<String, String>()

        if (repoLanguage != null && !repoLanguage.isEmpty())
            queryRepo += " language:$repoLanguage"
        query["q"] = queryRepo

        mService!!.searchRepositories(query).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    Log.i(TAG, "posts loaded from API $response")

                    browsedRepositories = response.body()!!.items

                    if (browsedRepositories!!.size > 0)
                        setupRecyclerView(browsedRepositories)
                    else
                        Util.showMessage(this@DisplayActivity, "No Items Found")

                } else {
                    Log.i(TAG, "error $response")
                    Util.showErrorMessage(this@DisplayActivity, response.errorBody()!!)
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Util.showMessage(this@DisplayActivity, t.toString())
            }
        })
    }

    private fun setupRecyclerView(items: List<Repository>?) {
        mDisplayAdapter = DisplayAdapter(this, items)
        recyclerView!!.adapter = mDisplayAdapter
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        menuItem.isChecked = true
        closeDrawer()

        when (menuItem.itemId) {

            R.id.item_bookmark -> {
                showBookmarks()
                supportActionBar!!.title = "Showing Bookmarks"
            }

            R.id.item_browsed_results -> {
                showBrowsedResults()
                supportActionBar!!.title = "Showing Browsed Results"
            }
        }

        return true
    }

    private fun showBrowsedResults() {
        mDisplayAdapter!!.swap(browsedRepositories)
    }

    private fun showBookmarks() {
        mRealm!!.executeTransaction { realm ->
            val repositories = realm.where(Repository::class.java).findAll()
            mDisplayAdapter!!.swap(repositories)
        }
    }

    private fun closeDrawer() {
        drawerLayout!!.closeDrawer(GravityCompat.START)
    }

    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START))
            closeDrawer()
        else {
            super.onBackPressed()
            mRealm!!.close()
        }
    }

    companion object {

        private val TAG = DisplayActivity::class.java.simpleName
    }
}
