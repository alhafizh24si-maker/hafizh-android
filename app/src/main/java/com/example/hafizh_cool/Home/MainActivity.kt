package com.example.hafizh_cool.Home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.hafizh_cool.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Inisialisasi Toolbar (Penting untuk CoordinatorLayout)
        setSupportActionBar(binding.toolbar)
        // Kita kosongkan title aslinya karena kita sudah pakai TextView khusus di XML
        supportActionBar?.title = ""

        Log.e("Lifecycle", "MainActivity: onCreate")

        // 2. Setup semua listener tombol
        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Tombol 1: Rumus Bangun Ruang
        binding.btnRumus.setOnClickListener {
            val intent = Intent(this, kalkulator::class.java).apply {
                putExtra("EXTRA_TITLE", "Kalkulator Geometri")
                putExtra("EXTRA_DESC", "Hitung Luas Jajar Genjang & Volume Kubus")
            }
            startActivity(intent)
        }

        // Tombol 2: Custom 1 (Informasi)
        binding.btnCustom1.setOnClickListener {
            val intent = Intent(this, CustomOneActivity::class.java).apply {
                putExtra("EXTRA_TITLE", "Halaman Galeri")
                putExtra("EXTRA_DESC", "Menampilkan koleksi gambar pilihan")
            }
            startActivity(intent)
        }

        // Tombol 3: Custom 2 (Profil)
        binding.btnCustom2.setOnClickListener {
            val intent = Intent(this, CustomTwoActivity::class.java).apply {
                putExtra("EXTRA_TITLE", "Informasi Akun")
                putExtra("EXTRA_DESC", "Detail profil dan pengaturan aplikasi")
            }
            startActivity(intent)
        }

        // Tombol 4: Web Portal Bina Desa (WebView)
        binding.btnWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }

        // Tombol 5: Logout dengan SharedPreferences
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    // Hapus Session Login (Materi Pertemuan 6)
                    val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                    sharedPref.edit().clear().apply()

                    // Pindah ke LoginActivity dan tutup MainActivity
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Lifecycle", "MainActivity: onDestroy")
    }
}