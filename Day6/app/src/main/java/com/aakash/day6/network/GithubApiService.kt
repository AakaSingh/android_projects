package com.aakash.day6.network
import com.aakash.day6.entities.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.github.com/users/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

//Defines how retrofit talks to the server using HTTP Requests
interface GithubApiService {

    @GET("{user}/followers")
    fun getFollowers(@Path("user") user: String): Call<List<User>>

    @GET("{user}")
    fun getUser(@Path("user") user: String) : Call<User>
}

object GithubApi{
    val retrofitService: GithubApiService by lazy {
        retrofit.create(GithubApiService::class.java)
    }
}