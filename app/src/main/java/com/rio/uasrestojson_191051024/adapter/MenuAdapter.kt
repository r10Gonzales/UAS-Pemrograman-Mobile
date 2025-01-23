package com.rio.uasrestojson_191051024.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rio.uasrestojson_191051024.DetailActivity
import com.rio.uasrestojson_191051024.R
import com.rio.uasrestojson_191051024.model.Menu

class MenuAdapter(
    private val context: Context,
    private val menuList: List<Menu>
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menuList[position]
        holder.bind(menu)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("menu_item", menu)
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int = menuList.size

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivMenuImage: ImageView = itemView.findViewById(R.id.ivMenuImage)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)

        fun bind(menu: Menu) {
            tvTitle.text = menu.strMeal
            tvCategory.text = menu.strCategory

            // Menggunakan Glide untuk memuat gambar
            Glide.with(context)
                .load(menu.strMealThumb)
                .into(ivMenuImage)
        }
    }
}
