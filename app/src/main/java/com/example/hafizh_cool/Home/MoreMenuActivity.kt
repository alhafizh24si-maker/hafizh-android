package com.example.hafizh_cool.Home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.hafizh_cool.R

class MoreMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_more_menu)

        val listView = findViewById<ListView>(R.id.lvMoreMenu)

        // Data Menu dan Ikon sesuai urutan
        val menuNames = arrayOf("Pusat Bantuan", "Kebijakan Privasi", "Syarat & Ketentuan", "Tentang Aplikasi", "Keluar")
        val menuIcons = arrayOf(
            android.R.drawable.ic_menu_help,
            android.R.drawable.ic_lock_lock,
            android.R.drawable.ic_menu_agenda,
            android.R.drawable.ic_menu_info_details,
            android.R.drawable.ic_menu_close_clear_cancel
        )

        val adapter = object : BaseAdapter() {
            override fun getCount(): Int = menuNames.size
            override fun getItem(position: Int): Any = menuNames[position]
            override fun getItemId(position: Int): Long = position.toLong()

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                val view = layoutInflater.inflate(R.layout.item_menu_custom, parent, false)
                val tvName = view.findViewById<TextView>(R.id.tvMenuName)
                val ivIcon = view.findViewById<ImageView>(R.id.ivMenuIcon)

                tvName.text = menuNames[position]
                ivIcon.setImageResource(menuIcons[position])

                // Logika warna merah untuk tombol Keluar
                if (menuNames[position] == "Keluar") {
                    tvName.setTextColor(Color.RED)
                    ivIcon.setColorFilter(Color.RED)
                }

                return view
            }
        }

        listView.adapter = adapter

        // Klik Listener
        listView.setOnItemClickListener { _, _, position, _ ->
            when (menuNames[position]) {
                "Keluar" -> finish()
                else -> Toast.makeText(this, "Membuka ${menuNames[position]}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}