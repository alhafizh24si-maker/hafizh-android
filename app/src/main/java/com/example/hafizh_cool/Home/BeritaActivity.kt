package com.example.hafizh_cool.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hafizh_cool.data.AppDatabase
import com.example.hafizh_cool.data.model.BeritaViewModel
import com.example.hafizh_cool.databinding.ActivityBeritaBinding
import com.example.hafizh_cool.databinding.DialogTambahBeritaBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BeritaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBeritaBinding
    private lateinit var viewModel: BeritaViewModel
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Inisialisasi Database Room
        database = AppDatabase.getInstance(this)

        // 2. Menggunakan Factory agar Android tidak Crash / Keep Closing
        val factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(BeritaViewModel::class.java)) {
                    return BeritaViewModel(database.beritaDao()) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
        viewModel = ViewModelProvider(this, factory)[BeritaViewModel::class.java]

        // Setup Toolbar
        setSupportActionBar(binding.toolbarBerita)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarBerita.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.rvBeritaAktivitas.layoutManager = LinearLayoutManager(this)

        // REALTIME OBSERVATION: Mendengarkan perubahan data Room lokal
        lifecycleScope.launch {
            database.beritaDao().getAllBeritaLocal().collectLatest { listBerita ->
                // 💡 PERBAIKAN AKTIF: Komentar dilepas agar data otomatis tampil ke UI!
                val adapterLocal = BeritaLocalAdapter(listBerita)
                binding.rvBeritaAktivitas.adapter = adapterLocal
            }
        }

        // Action Klik Floating Action Button (FAB)
        binding.fabTambahBerita.setOnClickListener {
            showTambahBeritaDialog()
        }
    }

    private fun showTambahBeritaDialog() {
        val dialogBinding = DialogTambahBeritaBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Tambah Informasi Desa")
            .setView(dialogBinding.root)
            .create()

        dialogBinding.btnSimpan.setOnClickListener {
            val judul = dialogBinding.etJudulBerita.text.toString().trim()
            val isi = dialogBinding.etIsiBerita.text.toString().trim()
            val url = dialogBinding.etUrlGambar.text.toString().trim()

            if (judul.isNotEmpty() && isi.isNotEmpty()) {
                viewModel.tambahBeritaBaru(judul, isi, url)
                Toast.makeText(this, "Berita berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Form judul & isi wajib diisi!", Toast.LENGTH_SHORT).show()
            }
        }

        dialogBinding.btnBatal.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}