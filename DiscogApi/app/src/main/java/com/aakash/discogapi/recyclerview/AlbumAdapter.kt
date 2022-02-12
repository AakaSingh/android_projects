package com.aakash.discogapi.recyclerview


import android.content.Context
import android.content.Intent
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

class AlbumAdapter(private val dataSet: List<Album>) : RecyclerView.Adapter<AlbumAdapter.AlbumItemViewHolder>(){
    class AlbumItemViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView){
        var album : Album? = null
        val backgroundImage: ImageView = containerView.findViewById(R.id.list_item_albumImage)
        val title: TextView = containerView.findViewById(R.id.list_item_title)
        val genre: TextView = containerView.findViewById(R.id.list_item_genre)

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return AlbumItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumItemViewHolder, position: Int) {
        val currentData = dataSet[position]
        Picasso.get()
            .load(currentData.thumbnailUrl)
            .into(holder.backgroundImage)
        holder.album = currentData
        holder.title.setText(currentData.albumTitle)
        holder.genre.setText(currentData.genre.toString())
    }

    override fun getItemCount() = dataSet.size
}