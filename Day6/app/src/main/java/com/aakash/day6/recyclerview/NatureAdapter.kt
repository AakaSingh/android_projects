package com.aakash.day6.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aakash.day6.R
import com.aakash.day6.entities.Nature

class NatureAdapter(private val dataSet: List<Nature>) : RecyclerView.Adapter<NatureAdapter.NatureItemViewHolder>(){
    class NatureItemViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView){
        val backgroundImage: ImageView = containerView.findViewById(R.id.item_list_image)
        val title: TextView = containerView.findViewById(R.id.item_list_title)

        init {
            //click listeners and whatever else needed to manage view
        }
    }

    //Inflate the custom view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NatureItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return NatureItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NatureItemViewHolder, position: Int) {
        val currentData = dataSet[position]
        holder.backgroundImage.setImageResource(currentData.image)
        holder.title.setText(currentData.title)
    }

    override fun getItemCount() = dataSet.size
}