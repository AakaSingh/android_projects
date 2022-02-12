package com.aakash.discogapi.entities

import com.google.gson.annotations.SerializedName

data class RawJsonData(
    @SerializedName("results") val albums : List<Album>
)
