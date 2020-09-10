package com.example.githubusers

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private var list: ArrayList<GithubUsers> = arrayListOf()
//    private val arrayNames: Array<String> = resources.getStringArray(R.array.name)
//    private val arrayUsernames: Array<String> = resources.getStringArray(R.array.username)
//    private val arrayImages: IntArray = resources.getIntArray(R.array.avatar)
    private lateinit var dataName: Array<String>
    private lateinit var dataDescription: Array<String>
    private lateinit var dataPhoto: TypedArray
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataFollowers: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepository: Array<String>
    //private var users = arrayListOf<GithubUsers>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.rv_users)
        rvUsers.setHasFixedSize(true)

        prepare()
        list.addAll(listData)
        showRecyclerCardView()
    }

    /*fun onClick(v: View) {
        when (v.id) {
            R.id.btn_detail -> {
                val user = GithubUsers(
                    dataName[position],
                    5,
                    "academy@dicoding.com",
                    "Bandung"
                )

                val moveWithObjectIntent = Intent(this@MainActivity, DetailFragment::class.java)
                moveWithObjectIntent.putExtra(DetailFragment.EXTRA_USER, user)
                startActivity(moveWithObjectIntent)
            }
        }
    }*/

    private fun showRecyclerCardView() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        val cardViewUserAdapter = CardViewUserAdapter(list)
        rvUsers.adapter = cardViewUserAdapter

        cardViewUserAdapter.setOnItemClickCallback(object : CardViewUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GithubUsers) {
                showSelectedUser(data)
            }
        })
    }

    private val listData: ArrayList<GithubUsers>
        get() {
            val list = arrayListOf<GithubUsers>()
            for (position in dataName.indices) {
                val user = GithubUsers(
                    name = dataName[position],
                    detail = dataDescription[position],
                    photo = dataPhoto.getResourceId(position, -1),
                    following = dataFollowing[position],
                    follower = dataFollowers[position],
                    company = dataCompany[position],
                    location = dataLocation[position],
                    repository = dataRepository[position]
                    )
                list.add(user)
            }
            return list
        }

    private fun showSelectedUser(user: GithubUsers) {
        //Toast.makeText(this, "Kamu memilih " + user.name, Toast.LENGTH_SHORT).show()
        val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveWithObjectIntent.putExtra(DetailActivity.EXTRA_USER, user)
        startActivity(moveWithObjectIntent)
    }

    /*private fun addItem() {
        for (position in dataName.indices) {
            val user = GithubUsers(
                dataName[position],
                dataPhoto.getResourceId(position, -1),
                dataDescription[position]
            )
            user.add(hero)
        }
        adapter.heroes = heroes
    }*/

    private fun prepare() {
        dataName = resources.getStringArray(R.array.name)
        dataDescription = resources.getStringArray(R.array.username)
        dataPhoto = resources.obtainTypedArray(R.array.avatar)
        dataFollowing = resources.getStringArray(R.array.following)
        dataFollowers = resources.getStringArray(R.array.followers)
        dataCompany = resources.getStringArray(R.array.company)
        dataLocation = resources.getStringArray(R.array.location)
        dataRepository = resources.getStringArray(R.array.repository)
    }
}
