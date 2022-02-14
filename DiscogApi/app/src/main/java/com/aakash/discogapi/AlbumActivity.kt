package com.aakash.discogapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.aakash.discogapi.db.FavoritesTable
import com.aakash.discogapi.entities.Album
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class AlbumActivity : AppCompatActivity() {

    private lateinit var favoritesTable : FavoritesTable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        val albumImage = findViewById<ImageView>(R.id.single_album_image)
        val albumTitle = findViewById<TextView>(R.id.single_album_name)
        val albumArtist = findViewById<TextView>(R.id.single_album_artist)
        val albumReleaseYear = findViewById<TextView>(R.id.single_album_year)
        val myIntent = intent
        if(intent.hasExtra(MainActivity.EXTRA_ALBUM_KEY)){

            val album: Album = intent.getSerializableExtra(MainActivity.EXTRA_ALBUM_KEY) as Album
            val albumInfo = album.albumTitle.split("-")
            Picasso.get()
                .load(album.thumbnailUrl)
                .into(albumImage)

            albumTitle.setText(albumInfo[1])
            albumArtist.setText(albumInfo[0])
            albumReleaseYear.setText(album.year)
        }
    }
}