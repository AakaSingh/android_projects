package com.aakash.discogapi.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * If you want to access database
    val dbHelper = DatingDbHelper(context)
 */

private const val SQL_CREATE_TABLE =
    "CREATE TABLE ${DiscogsDbContract.FavoritesTable.TABLE_NAME} (" +
            "${DiscogsDbContract.FavoritesTable.ID} INTEGER PRIMARY KEY, " + //"${BaseColumns._ID}"
            "${DiscogsDbContract.FavoritesTable.ALBUM_TITLE} TEXT, " +
            "${DiscogsDbContract.FavoritesTable.THUMBNAIL_URL} TEXT, " +
            "${DiscogsDbContract.FavoritesTable.GENRE} TEXT, " +
            "${DiscogsDbContract.FavoritesTable.STYLE} TEXT," +
            "${DiscogsDbContract.FavoritesTable.YEAR} TEXT" +
            ")"

private const val DROP_TABLE = "DROP TABLE IF EXISTS ${DiscogsDbContract.FavoritesTable.TABLE_NAME}"

class DiscogsDbHelper(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

        companion object {
            const val DATABASE_NAME = "discogs"
            const val DATABASE_VERSION = 1
        }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}


















