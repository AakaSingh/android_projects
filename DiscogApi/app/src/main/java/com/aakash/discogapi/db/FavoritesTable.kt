package com.aakash.discogapi.db

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.aakash.discogapi.entities.Album
import java.time.Year

class FavoritesTable(context: Context) {
    private val dbHelper = DiscogsDbHelper(context)

    fun insertData(albumTitle: String, thumbnailUrl: String, genre: List<String>, style : List<String>, releaseYear: String): Long{
        //Map of column name + row value
        val values = ContentValues().apply {
            put(DiscogsDbContract.FavoritesTable.ALBUM_TITLE, albumTitle)
            put(DiscogsDbContract.FavoritesTable.THUMBNAIL_URL, thumbnailUrl)
            put(DiscogsDbContract.FavoritesTable.GENRE, genre.joinToString(","))
            put(DiscogsDbContract.FavoritesTable.STYLE, style.joinToString(","))
            put(DiscogsDbContract.FavoritesTable.YEAR, releaseYear)
        }

        val writeToDb = dbHelper.writableDatabase
        val newRowId = writeToDb.insert(DiscogsDbContract.FavoritesTable.TABLE_NAME, null, values)
        return newRowId
    }

    fun getAll(): List<Album> {
        val readFromDb = dbHelper.readableDatabase //EXPENSIVE if DB is closed.

        //Select Columns you want
        val projection = arrayOf(
            DiscogsDbContract.FavoritesTable.ID,
            DiscogsDbContract.FavoritesTable.ALBUM_TITLE,
            DiscogsDbContract.FavoritesTable.GENRE,
            DiscogsDbContract.FavoritesTable.STYLE,
            DiscogsDbContract.FavoritesTable.THUMBNAIL_URL,
            DiscogsDbContract.FavoritesTable.YEAR,
        )

        //WHERE PART only to avoid SQL Injection
        //val selection = "${DatingDBContract.UserTable.USERNAME} = ? AND ${DatingDBContract.UserTable.PASSWORD} = ?"
        //val selectionArgs = arrayOf("rezaUser", "rezaPassword")




        val cursor = readFromDb.query(
            DiscogsDbContract.FavoritesTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val albumsList = mutableListOf<Album>()

        with(cursor) {
            while (moveToNext()) {//Moves from -1 row to next one
                val album = Album(
                    getString(getColumnIndexOrThrow(DiscogsDbContract.FavoritesTable.ALBUM_TITLE)),
                    getString(getColumnIndexOrThrow(DiscogsDbContract.FavoritesTable.THUMBNAIL_URL)),
                    getString(getColumnIndexOrThrow(DiscogsDbContract.FavoritesTable.GENRE)).split(","),
                    getString(getColumnIndexOrThrow(DiscogsDbContract.FavoritesTable.STYLE)).split(","),
                    getString(getColumnIndexOrThrow(DiscogsDbContract.FavoritesTable.YEAR)),
                    getLong(getColumnIndexOrThrow(DiscogsDbContract.FavoritesTable.ID))
                )
                albumsList.add(album)
            }
        }
        Log.e("album list length",albumsList.count().toString())
        cursor.close()
        return albumsList
    }

    fun getByAlbumTitle( albumTitle: String): Album?{
        val readFromDb = dbHelper.readableDatabase //EXPENSIVE if DB is closed.

        //Select Columns you want
        val projection = arrayOf(
            DiscogsDbContract.FavoritesTable.ID,
            DiscogsDbContract.FavoritesTable.ALBUM_TITLE,
            DiscogsDbContract.FavoritesTable.GENRE,
            DiscogsDbContract.FavoritesTable.STYLE,
            DiscogsDbContract.FavoritesTable.THUMBNAIL_URL,
            DiscogsDbContract.FavoritesTable.YEAR,
        )

        //WHERE PART only to avoid SQL Injection
        val selection = "${DiscogsDbContract.FavoritesTable.ALBUM_TITLE} = ?"
        val selectionArgs = arrayOf(albumTitle)

        val cursor = readFromDb.query(
            DiscogsDbContract.FavoritesTable.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var album : Album? = null

        if(cursor.count == 0)
            return null

        with(cursor) {
            while (moveToNext()) {//Moves from -1 row to next one
                Log.e("here","there")
                album = Album(
                    getString(getColumnIndexOrThrow(DiscogsDbContract.FavoritesTable.ALBUM_TITLE)),
                    getString(getColumnIndexOrThrow(DiscogsDbContract.FavoritesTable.THUMBNAIL_URL)),
                    getString(getColumnIndexOrThrow(DiscogsDbContract.FavoritesTable.GENRE)).split(","),
                    getString(getColumnIndexOrThrow(DiscogsDbContract.FavoritesTable.STYLE)).split(","),
                    getString(getColumnIndexOrThrow(DiscogsDbContract.FavoritesTable.YEAR)),
                    getLong(getColumnIndexOrThrow(DiscogsDbContract.FavoritesTable.ID))
                )
            }
        }
        cursor.close()
        return album
    }


    fun delete(album: Album) : Boolean {

        val dbWrite = dbHelper.writableDatabase

        val whereClause = "${DiscogsDbContract.FavoritesTable.ID} LIKE ?"
        val whereClauseArgs = arrayOf(album.id.toString())

        val deletedRows = dbWrite.delete(
            DiscogsDbContract.FavoritesTable.TABLE_NAME,
            whereClause,
            whereClauseArgs
        )

        return deletedRows > 0
    }
}














