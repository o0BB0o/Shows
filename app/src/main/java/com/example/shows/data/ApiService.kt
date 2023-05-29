package com.example.shows.data
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

object ApiService {
    private val client = OkHttpClient()

    fun getShow(): List<Show> {
        val request = Request.Builder()
            .url("https://libertybloom.com/wp-json/past/v1/events")
            .build()
        val response = client.newCall(request).execute()
        val json = response.body?.string()
        return Gson().fromJson(json, Array<Show>::class.java).toList()
    }
}