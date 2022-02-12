package com.aakash.discogapi.network

import com.aakash.discogapi.entities.Album
import com.aakash.discogapi.entities.RawJsonData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.discogs.com/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface DiscogApiService {
    @GET("/database/search")
    fun getAlbums(
        @Query("key") key: String = "xLxRhchBfxfAOtMiCAxV",
        @Query("secret") secret: String = "WzSiMvgXqyscrehWUFYekOIVICGYkNmH",
        @Query("artist") artist :String,
        @Query("country") country: String = "canada"
    ) : Call<RawJsonData>
}

object DiscogsApi{
    val retrofitService: DiscogApiService by lazy {
        retrofit.create(DiscogApiService::class.java)
    }
}