package com.example.githubusers.viewmodel

import android.util.Log
import com.example.githubusers.model.User
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class MainViewModel {

    private fun getUsers() {
        val TAG: String = MainViewModel::class.java.name
        val client = AsyncHttpClient()
        val url = "https://api.github.com/search/users?q=rez"
        client.addHeader("Authorization", "token 5dcc658440f621577f043220acb36f037a17bed6")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                // if connection success
                val listUser = ArrayList<User>()

                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val responseObject = JSONObject(result)
                    val items = responseObject.getJSONArray("items")

                    for (i in 0 until items.length()) {
                        val item = items.getJSONObject(i)
                        val username = item.getString("login")
                        val avatar = item.getString("avatar_url")
                        val user = User(username, avatar)
                        user.username = username
                        user.avatar = avatar
                        listUser.add(user)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                // if connection fail
                val errorMessage = when (statusCode) {
                    400 -> "$statusCode : Bad Request"
                    401 -> "$statusCode : Unauthorized"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
            }
        })
    }
}