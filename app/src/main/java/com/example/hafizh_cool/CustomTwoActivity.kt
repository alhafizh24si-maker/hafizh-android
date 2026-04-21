package com.example.hafizh_cool

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.hafizh_cool.databinding.ActivityCustomTwoBinding

class CustomTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi View Binding
        binding = ActivityCustomTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("Lifecycle", "CustomTwoActivity: onCreate dipanggil")

        // 2. Setup Toolbar (Pengganti btnBackIcon dan tvTitleHalaman manual)
        setSupportActionBar(binding.toolbar)

        // 3. Ambil data dari Intent
        val judul = intent.getStringExtra("EXTRA_TITLE") ?: "Profil Saya"
        val namaUser = intent.getStringExtra("EXTRA_NAME") ?: "Hafizh Binadesa"
        val emailUser = intent.getStringExtra("EXTRA_EMAIL") ?: "hafizh.cool@binadesa.id"

        // 4. Konfigurasi SupportActionBar
        supportActionBar?.apply {
            title = judul // Judul tampil di Toolbar
            setDisplayHomeAsUpEnabled(true) // Munculkan panah kembali (<-)
            setDisplayShowHomeEnabled(true)
        }

        // 5. Tampilkan data ke komponen UI
        binding.tvProfileName.text = namaUser
        binding.tvProfileEmail.text = emailUser

        // Mengisi detail kartu
        binding.tvFullNameDisplay.text = intent.getStringExtra("EXTRA_FULL_NAME") ?: "Hafizh Binadesa"
        binding.tvPhoneDisplay.text = intent.getStringExtra("EXTRA_PHONE") ?: "+62 812 3456 7890"

        // 6. Logika Tombol Kembali (Button besar di bawah)
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 7. Logika Tombol Edit Foto (Floating Action Button)
        binding.btnEditPhoto.setOnClickListener {
            Log.d("Profile", "Tombol edit foto diklik")
        }
    }

    // 8. Fungsi agar tombol panah di Toolbar berfungsi secara standar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Lifecycle", "CustomTwoActivity: onDestroy (Activity dihancurkan)")
    }
}