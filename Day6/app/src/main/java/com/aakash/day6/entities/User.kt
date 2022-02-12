package com.aakash.day6.entities

import com.google.gson.annotations.SerializedName

data class User(
    var login: String,
    @SerializedName("avatar_url")var avatarUrl: String,
    var url: String,
    @SerializedName("html_url") var htmlUrl: String
){}
