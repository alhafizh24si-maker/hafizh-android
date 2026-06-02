package com.example.hafizh_cool.note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hafizh_cool.data.AppDatabase
import com.example.hafizh_cool.data.api.entity.NoteEntity
import com.example.hafizh_cool.databinding.FragmentNotesBinding
import kotlinx.coroutines.launch

class FragmentNotes : Fragment() {

    // 1. Inisialisasi ViewBinding untuk Fragment
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    // 2. Deklarasi komponen Database dan Adapter
    private lateinit var adapter: NoteAdapter
    private lateinit var db: AppDatabase
    private val notesList = mutableListOf<NoteEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout menggunakan ViewBinding
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 3. Inisialisasi AppDatabase instance
        db = AppDatabase.getInstance(requireContext())

        // 4. Setup RecyclerView & Adapter (Mengirim 'this' untuk handling hapus data)
        adapter = NoteAdapter(notesList, this)
        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = adapter

        // 5. Tambahkan garis pemisah (Divider) antar item agar rapi
        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.rvNotes.addItemDecoration(dividerItemDecoration)

        // 6. Jalankan FAB untuk membuka NoteFormActivity
        binding.fabAddNote.setOnClickListener {
            val intent = Intent(requireContext(), NoteFormActivity::class.java)
            startActivity(intent)
        }

        // Ambil data pertama kali fragment dibuat
        fetchNotes()
    }

    // 7. Fungsi mengambil data dari Room Database menggunakan Coroutine Scope
    private fun fetchNotes() {
        lifecycleScope.launch {
            val data = db.noteDao().getAll() // Memanggil Query SELECT * FROM notes
            notesList.clear()
            notesList.addAll(data)
            adapter.notifyDataSetChanged() // Beritahu adapter bahwa data berubah
        }
    }

    // 8. Fungsi hapus data yang nantinya dipanggil dari dalam NoteAdapter
    fun deleteNote(note: NoteEntity) {
        lifecycleScope.launch {
            db.noteDao().delete(note) // Jalankan query DELETE
            fetchNotes() // Tarik ulang data terbaru dari SQLite
        }
    }

    // 9. Ambil data ulang secara otomatis ketika form input ditutup (kembali ke fragment ini)
    override fun onResume() {
        super.onResume()
        fetchNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Menghindari memory leak
    }
}