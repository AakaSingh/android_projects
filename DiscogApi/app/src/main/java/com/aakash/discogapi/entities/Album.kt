package com.aakash.discogapi.entities

import android.provider.MediaStore
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Album (
    @SerializedName("title") val albumTitle : String,
    @SerializedName("thumb") val thumbnailUrl : String,
    val genre : List<String>,
    val style : List<String>,
    val year : String
) : Serializable{
}
