package com.aakash.discogapi.db

import android.provider.BaseColumns

object DiscogsDbContract {
    //Tables
    object FavoritesTable : BaseColumns {
        const val ID = "id"
        const val TABLE_NAME = "favorites"
        const val ALBUM_TITLE= "album_title"
        const val THUMBNAIL_URL = "thumbnail_url"
        const val GENRE= "genre"
        const val STYLE = "style"
        const val YEAR = "release_year"
    }
}