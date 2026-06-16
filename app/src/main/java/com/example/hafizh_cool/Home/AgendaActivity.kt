package com.example.hafizh_cool.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hafizh_cool.data.AppDatabase
import com.example.hafizh_cool.data.model.AgendaViewModel
import com.example.hafizh_cool.data.utils.NotificationHelper
import com.example.hafizh_cool.data.utils.PermissionHelper
import com.example.hafizh_cool.data.utils.ReminderHelper
import com.example.hafizh_cool.databinding.ActivityAgendaBinding
import com.example.hafizh_cool.databinding.DialogTambahAgendaBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar

class AgendaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgendaBinding
    private lateinit var viewModel: AgendaViewModel
    private lateinit var database: AppDatabase

    // Launcher Izin Notifikasi untuk Android 13 (Tiramisu) ke atas
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Akses notifikasi diaktifkan!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Akses notifikasi ditolak. Anda tidak akan menerima pengingat.", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // [1] Minta Izin Notifikasi otomatis saat user memasuki halaman Agenda Desa
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(notificationPermissionLauncher, permission)
            }
        }

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

            // Mengambil nilai komponen EditText pengingat baru
            val inputMenit = dialogBinding.etMenitReminder.text.toString().trim()

            if (judul.isNotEmpty() && tanggal.isNotEmpty()) {
                // Simpan data ke dalam Database SQLite Local via ViewModel
                viewModel.tambahAgendaBaru(judul, tanggal, lokasi, keterangan)

                // [2] LOCAL NOTIFICATION: Muncul instan untuk konfirmasi sukses input
                val intentNotif = Intent(this, AgendaActivity::class.java)
                NotificationHelper.showNotification(
                    context = this,
                    title = "Agenda Desa Berhasil Dibuat!",
                    message = "Kegiatan '$judul' telah terdaftar di database.",
                    intent = intentNotif
                )

                // [3] REMINDER MANAGER: Set pengingat latar belakang jika kolom diisi
                if (inputMenit.isNotEmpty()) {
                    val menit = inputMenit.toInt()
                    val calendar = Calendar.getInstance().apply {
                        add(Calendar.MINUTE, menit) // Kalkulasi target waktu (Waktu sekarang + X Menit)
                    }

                    ReminderHelper.setReminder(
                        context = this,
                        hour = calendar.get(Calendar.HOUR_OF_DAY),
                        minute = calendar.get(Calendar.MINUTE),
                        title = "Pengingat Jadwal: $judul",
                        message = "Kegiatan desa di $lokasi akan segera dimulai! Bersiap-siap.",
                        targetActivity = AgendaActivity::class.java
                    )
                    Toast.makeText(this, "Agenda disimpan & pengingat $menit menit aktif!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Agenda berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                }

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