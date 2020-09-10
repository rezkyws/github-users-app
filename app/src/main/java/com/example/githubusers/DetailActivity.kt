package com.example.githubusers

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.LocaleList
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvName: TextView = findViewById(R.id.tv_detail_name)
        val tvUsername: TextView = findViewById(R.id.tv_detail_username)
        val tvFollowing: TextView = findViewById(R.id.tv_detail_following)
        val tvFollowers: TextView = findViewById(R.id.tv_detail_followers)
        val tvCompany: TextView = findViewById(R.id.tv_detail_company)
        val tvLocation: TextView = findViewById(R.id.tv_detail_location)
        val tvRepository: TextView = findViewById(R.id.tv_detail_repository)
        val imgPhoto: ImageView = findViewById(R.id.img_detail_photo)

        val user = intent.getParcelableExtra(EXTRA_USER) as GithubUsers
        val name = "Name : ${user.name.toString()}"
        val username = "Username : ${user.detail.toString()}"
        val following = "Following : ${user.following.toString()}"
        val followers = "Followers : ${user.follower.toString()}"
        val company = "Company : ${user.company.toString()}"
        val location = "Location : ${user.location.toString()}"
        val repository = "Repository : ${user.repository.toString()}"
        val photo = user.photo!!.toInt()

        tvName.text = name
        tvUsername.text = username
        tvFollowing.text = following
        tvFollowers.text = followers
        tvCompany.text = company
        tvLocation.text = location
        tvRepository.text = repository
        imgPhoto.setImageResource(photo)
    }
}
