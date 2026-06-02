package com.example.hafizh_cool.note

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.hafizh_cool.R
import com.example.hafizh_cool.data.AppDatabase
import com.example.hafizh_cool.data.api.entity.NoteEntity
import com.example.hafizh_cool.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Setup ViewBinding dengan benar
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handling Window Insets untuk Edge-to-Edge screen
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2. Setup Toolbar Form
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Tambah Catatan Baru"
        binding.toolbar.setNavigationOnClickListener {
            finish() // Menutup activity saat tombol back di toolbar diklik
        }

        // 3. Inisialisasi Database Room Instance
        db = AppDatabase.getInstance(this)

        // 4. Logika Klik Tombol Simpan
        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val content = binding.etContent.text.toString().trim()

            // Validasi input tidak boleh kosong
            if (title.isNotBlank() && content.isNotBlank()) {

                // Penggunaan Coroutine (lifecycleScope) untuk operasi database di background thread
                lifecycleScope.launch {
                    val note = NoteEntity(
                        title = title,
                        content = content,
                        createdAt = System.currentTimeMillis()
                    )

                    // Eksekusi insert data melalui DAO
                    db.noteDao().insert(note)

                    // Tampilkan feedback sukses ke user
                    Toast.makeText(this@NoteFormActivity, "Catatan berhasil disimpan!", Toast.LENGTH_SHORT).show()

                    // Tutup form dan kembali ke FragmentNote
                    finish()
                }

            } else {
                // Jika inputan ada yang kosong
                Toast.makeText(this, "Isi semua kolom terlebih dahulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}