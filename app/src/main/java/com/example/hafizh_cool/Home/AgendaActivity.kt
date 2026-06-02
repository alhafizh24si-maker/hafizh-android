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
import com.example.hafizh_cool.data.model.AgendaViewModel
import com.example.hafizh_cool.databinding.ActivityAgendaBinding
import com.example.hafizh_cool.databinding.DialogTambahAgendaBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AgendaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgendaBinding
    private lateinit var viewModel: AgendaViewModel
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getInstance(this)

        val factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(AgendaViewModel::class.java)) {
                    return AgendaViewModel(database.agendaDao()) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
        viewModel = ViewModelProvider(this, factory)[AgendaViewModel::class.java]

        setSupportActionBar(binding.toolbarAgenda)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarAgenda.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.rvAgendaAktivitas.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            database.agendaDao().getAllAgendaLocal().collectLatest { listAgenda ->
                val adapterLocal = AgendaLocalAdapter(listAgenda)
                binding.rvAgendaAktivitas.adapter = adapterLocal
            }
        }

        binding.fabTambahAgenda.setOnClickListener {
            showTambahAgendaDialog()
        }
    }

    private fun showTambahAgendaDialog() {
        val dialogBinding = DialogTambahAgendaBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Tambah Agenda Desa")
            .setView(dialogBinding.root)
            .create()

        dialogBinding.btnSimpan.setOnClickListener {
            val judul = dialogBinding.etJudulAgenda.text.toString().trim()
            val tanggal = dialogBinding.etTanggalAgenda.text.toString().trim()
            val lokasi = dialogBinding.etLokasiAgenda.text.toString().trim()
            val keterangan = dialogBinding.etKeteranganAgenda.text.toString().trim()

            if (judul.isNotEmpty() && tanggal.isNotEmpty()) {
                viewModel.tambahAgendaBaru(judul, tanggal, lokasi, keterangan)
                Toast.makeText(this, "Agenda berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Nama & Tanggal wajib diisi!", Toast.LENGTH_SHORT).show()
            }
        }

        dialogBinding.btnBatal.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}