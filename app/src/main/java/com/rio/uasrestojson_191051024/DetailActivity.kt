package com.rio.uasrestojson_191051024

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rio.uasrestojson_191051024.model.Menu

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Mendapatkan data yang dikirim dari MainActivity
        val menu = intent.getParcelableExtra<Menu>("menu_item")

        if (menu != null) {
            val ivMenuImage: ImageView = findViewById(R.id.ivMenuImage)
            val tvTitle: TextView = findViewById(R.id.tvTitle)
            val tvCategory: TextView = findViewById(R.id.tvCategory)
            val tvDescription: TextView = findViewById(R.id.tvDescription)

            // Mengisi data ke view
            tvTitle.text = menu.strMeal
            tvCategory.text = menu.strCategory
            tvDescription.text = menu.strInstructions.replace("\r\n", "\n") // Menampilkan instruksi/deskripsi dari API

            // Menampilkan gambar menu
            Glide.with(this)
                .load(menu.strMealThumb)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_error)
                .into(ivMenuImage)
        }
    }
}
