package com.aakash.discogapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.aakash.discogapi.entities.RawJsonData
import com.aakash.discogapi.network.DiscogsApi
import com.aakash.discogapi.recyclerview.AlbumAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_ALBUM_KEY = "album"
    }

    private lateinit var searchBar : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBar = findViewById(R.id.main_searchBar)
        val searchButton = findViewById<Button>(R.id.main_searchButton)
        searchButton.setOnClickListener(this::onSearchButtonClicked)
    }

    private fun onSearchButtonClicked(view: View){
        val callAlbums = DiscogsApi.retrofitService.getAlbums(artist = searchBar.text.toString())
        callAlbums.enqueue(object : Callback<RawJsonData> {
            override fun onResponse(call: Call<RawJsonData>, response: Response<RawJsonData>) {
                val dataReceived = response.body() as RawJsonData
                val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerView)
                recyclerView.adapter = AlbumAdapter(dataReceived.albums)
            }

            override fun onFailure(call: Call<RawJsonData>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}