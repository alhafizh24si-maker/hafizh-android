package com.example.hafizh_cool

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.hafizh_cool.databinding.ActivityCustomOneBinding

class CustomOneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar
        setSupportActionBar(binding.toolbar)

        // 2. Ambil data Intent (Perhatikan Kuncinya/Key)
        val judul = intent.getStringExtra("EXTRA_TITLE") ?: "Detail Fitur"

        // Perbaikan: Jangan masukkan deskripsi panjang sebagai default di sini
        // agar kamu tahu jika data dari Intent memang tidak masuk.
        val deskripsi = intent.getStringExtra("EXTRA_DESC") ?: "Deskripsi tidak ditemukan."

        supportActionBar?.apply {
            title = judul
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // 3. Update Konten (Pastikan ID tvDescription sesuai dengan di XML)
        binding.tvDescription.text = deskripsi

        // 4. Logika Tombol Kembali
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}