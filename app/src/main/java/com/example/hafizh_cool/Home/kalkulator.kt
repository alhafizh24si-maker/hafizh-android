package com.example.hafizh_cool.Home

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hafizh_cool.R

class kalkulator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kalkulator)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val judul = intent.getStringExtra("EXTRA_TITLE") ?: "Kalkulator Geometri"
        val desc = intent.getStringExtra("EXTRA_DESC") ?: "Hitung Luas Jajar Genjang & Volume Kubus"

        val tvJudul = findViewById<TextView>(R.id.tvTitleHalaman)
        val tvDeskripsi = findViewById<TextView>(R.id.tvDescHalaman)

        tvJudul.text = judul
        tvDeskripsi.text = desc

        // Set teksnya
        tvJudul.text = judul
        tvDeskripsi.text = desc

        Log.e("Data Intent", "Halaman: $judul | Deskripsi: $desc")


        // --- 2. INISIALISASI KOMPONEN HITUNGAN (Sesuai kode kamu) ---
        val etAlas = findViewById<EditText>(R.id.etAlas)
        val etTinggi = findViewById<EditText>(R.id.etTinggi)
        val btnHitungDatar = findViewById<Button>(R.id.btnHitungDatar)
        val tvHasilDatar = findViewById<TextView>(R.id.tvHasilDatar)

        val etSisi = findViewById<EditText>(R.id.etSisi)
        val btnHitungRuang = findViewById<Button>(R.id.btnHitungRuang)
        val tvHasilRuang = findViewById<TextView>(R.id.tvHasilRuang)

        // LOGIKA HITUNG LUAS JAJAR GENJANG
        btnHitungDatar.setOnClickListener {
            val alasStr = etAlas.text.toString()
            val tinggiStr = etTinggi.text.toString()

            if (alasStr.isNotEmpty() && tinggiStr.isNotEmpty()) {
                val alas = alasStr.toDouble()
                val tinggi = tinggiStr.toDouble()
                val luas = alas * tinggi
                tvHasilDatar.text = "Hasil Luas: $luas"
                Log.d("PENGHITUNG_APP", "Menghitung Jajar Genjang: $alas * $tinggi = $luas")
            } else {
                tvHasilDatar.text = "Mohon isi semua input!"
            }
        }

        // LOGIKA HITUNG VOLUME KUBUS
        btnHitungRuang.setOnClickListener {
            val sisiStr = etSisi.text.toString()

            if (sisiStr.isNotEmpty()) {
                val sisi = sisiStr.toDouble()
                val volume = sisi * sisi * sisi
                tvHasilRuang.text = "Hasil Volume: $volume"
                Log.i("PENGHITUNG_APP", "Menghitung Volume Kubus: $sisi^3 = $volume")
            } else {
                tvHasilRuang.text = "Mohon isi panjang sisi!"
            }
        }
    }
}