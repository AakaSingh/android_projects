package com.aakash.day6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aakash.day6.entities.User
import com.aakash.day6.network.GithubApi
import com.aakash.day6.network.GithubApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiData : AppCompatActivity(), retrofit2.Callback<User> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_data)

        val callUser = GithubApi.retrofitService.getUser("AakaSingh")
        callUser.enqueue(this)

        val callFollowers = GithubApi.retrofitService.getFollowers("MadReza")
        callFollowers.enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val listOfFollowers = response.body()
                if(listOfFollowers != null){
                    for (follower in listOfFollowers){
                        Log.e("Follower : ", follower.login)
                    }
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onResponse(call: Call<User>, response: Response<User>) {
        val user = response.body()
        if(user != null){
            Log.e("User : ",user.login)
        } else{
            Log.e("User : ","user does not exist")
        }
    }

    override fun onFailure(call: Call<User>, t: Throwable) {
        TODO("Not yet implemented")
    }
}