package com.aakash.discogapi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.aakash.discogapi.db.FavoritesTable
import com.aakash.discogapi.entities.Album
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class AlbumActivity : AppCompatActivity() {

    private lateinit var favoritesTable : FavoritesTable
    private lateinit var album: Album

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        val albumImage = findViewById<ImageView>(R.id.single_album_image)
        val albumTitle = findViewById<TextView>(R.id.single_album_name)
        val albumArtist = findViewById<TextView>(R.id.single_album_artist)
        val albumReleaseYear = findViewById<TextView>(R.id.single_album_year)
        val addOrDelete = findViewById<Button>(R.id.single_album_addOrDelete)
        favoritesTable = FavoritesTable(this)

        if(intent.hasExtra(MainActivity.EXTRA_ALBUM_KEY)){

            album = intent.getSerializableExtra(MainActivity.EXTRA_ALBUM_KEY) as Album
            val albumInfo = album.albumTitle.split("-")
            Picasso.get()
                .load(album.thumbnailUrl)
                .into(albumImage)

            albumTitle.text = albumInfo[1]
            albumArtist.text = albumInfo[0]
            albumReleaseYear.text = album.year
        }

        if(favoritesTable.getByAlbumTitle(album.albumTitle) != null)
        {
            Log.e("album title:",album.albumTitle)
            Log.e("here:","Here")
            addOrDelete.text = "Delete"
            addOrDelete.setBackgroundColor(Color.RED)
        }else{
            addOrDelete.text = "Add"
            addOrDelete.setBackgroundColor(Color.GREEN)
        }

        addOrDelete.setOnClickListener(this::onAddOrDeleteClicked)
    }

    private fun onAddOrDeleteClicked(view: View){
        var button = view as Button
        if(button.text == "Add"){
            album.id = favoritesTable.insertData(album.albumTitle, album.thumbnailUrl, album.genre, album.style, album.year)
            button.text = "Delete"
            button.setBackgroundColor(Color.RED)
        }
        else{
            favoritesTable.delete(album)
            button.text = "Add"
            button.setBackgroundColor(Color.GREEN)
        }
    }

}