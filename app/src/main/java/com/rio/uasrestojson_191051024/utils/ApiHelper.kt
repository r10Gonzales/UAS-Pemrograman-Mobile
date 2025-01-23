package com.rio.uasrestojson_191051024.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rio.uasrestojson_191051024.model.Menu
import okhttp3.OkHttpClient
import okhttp3.Request

object ApiHelper {

    private val client = OkHttpClient()

    fun getListMenu(apiUrl: String): List<Menu> {
        val request = Request.Builder().url(apiUrl).build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw Exception("HTTP error code: ${response.code}")
            val type = object : TypeToken<List<Menu>>() {}.type
            return Gson().fromJson(response.body?.string(), type)
        }
    }
}