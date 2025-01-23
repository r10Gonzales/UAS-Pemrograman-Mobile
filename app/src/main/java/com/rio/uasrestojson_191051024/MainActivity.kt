package com.rio.uasrestojson_191051024

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rio.uasrestojson_191051024.adapter.MenuAdapter
import com.rio.uasrestojson_191051024.model.Menu
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        getListMenu()
    }

    private fun getListMenu() {
        val url = "https://www.themealdb.com/api/json/v1/1/search.php?s="
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { json ->
                    val type = object : TypeToken<Map<String, List<Menu>>>() {}.type
                    val responseMap: Map<String, List<Menu>> = Gson().fromJson(json, type)
                    val menus = responseMap["meals"] ?: emptyList()

                    runOnUiThread {
                        if (menus.isEmpty()) {
                            Toast.makeText(this@MainActivity, "No data found", Toast.LENGTH_SHORT).show()
                        } else {
                            recyclerView.adapter = MenuAdapter(this@MainActivity, menus)
                        }
                    }
                }
            }
        })
    }
}
