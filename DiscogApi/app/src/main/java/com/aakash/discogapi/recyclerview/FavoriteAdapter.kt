package com.aakash.discogapi.recyclerview

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.aakash.discogapi.AlbumActivity
import com.aakash.discogapi.MainActivity
import com.aakash.discogapi.R
import com.aakash.discogapi.entities.Album
import com.squareup.picasso.Picasso

class FavoriteAdapter(private val dataSet: List<Album>) : RecyclerView.Adapter<FavoriteAdapter.FavoriteItemViewHolder>(){
    class FavoriteItemViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView){
        var album : Album? = null
        var cover : ImageView = containerView.findViewById(R.id.favorite_item_image)
        var title: TextView = containerView.findViewById(R.id.favorite_item_title)

        init {
            containerView.setOnClickListener{
                val intent = Intent(containerView.context,AlbumActivity::class.java).apply{
                    putExtra(MainActivity.EXTRA_ALBUM_KEY,album)
                }
                startActivity(containerView.context,intent,null)
            }
        }
    }
    //Inflate the custom view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_item, parent, false)
        return FavoriteItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteItemViewHolder, position: Int) {
        val currentData = dataSet[position]
        Picasso.get()
            .load(currentData.thumbnailUrl)
            .into(holder.cover)
        holder.album = currentData
        holder.title.text = currentData.albumTitle
    }

    override fun getItemCount() = dataSet.size
}