package com.aakash.discogapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.aakash.discogapi.db.FavoritesTable
import com.aakash.discogapi.recyclerview.AlbumAdapter
import com.aakash.discogapi.recyclerview.FavoriteAdapter

class FavoritesActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var favoritesTable: FavoritesTable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        favoritesTable = FavoritesTable(this)
        recyclerView = findViewById(R.id.favorites_recycler)
        val recyclerAdapter = FavoriteAdapter(favoritesTable.getAll())
        Log.e("data count",recyclerAdapter.itemCount.toString())
        recyclerView.adapter = recyclerAdapter
    }
}